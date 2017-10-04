package com.example.fifteam.tickettoride.model;

import android.util.Log;
import android.util.MonthDisplayHelper;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.CreateGameAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.GetGameListAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.JoinGameAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.LeaveGameAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.LoginAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.LogoutAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.RegisterAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.model.classes.login.BaseGameSummary;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.Calendar;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A façade for client-side interactions with the data stored in the model, so that other classes
 * need not bother themselves with any of how the model is structured internally, just asking for
 * the info they need and being given it.
 *
 * Created by kcwillmore on 9/28/17.
 */
public class ClientFacade{

    private static ClientFacade ourInstance = new ClientFacade();
    private ClientModel model = ClientModel.getInstance();
    private Timer timer;




    public static ClientFacade getInstance(){
        return ourInstance;
    }

    private ClientFacade(){
    }
    /**
     * unsure if we want error checking here, for now I will leave it without but I assume we will want it going forward
     * Sam
     *
     */
    public void login(String username, String password, Toaster toaster) {

        try {
            new LoginAsyncTask(toaster).execute(username, password).get();
        }
        catch (Exception e){
            //Log.e(null, "login: ",e);
            toaster.displayMessage(e.getMessage());
            return;
        }
    }

    /**
     *
     * @param username username of the user to be registerd
     * @param password password of the user to be registered
     */
    public void register(String username, String password, Toaster toaster) {
       try{
           new RegisterAsyncTask(toaster).execute(username,password).get();
       }
       catch (Exception e){
           toaster.displayMessage(e.getMessage());
           return;
       }

    }

    private void startPollerTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runPoller();
            }

        };
        this.timer.scheduleAtFixedRate(task,0,2000);
    }

    private void runPoller(){
        try{
            new GetGameListAsyncTask().execute();
        }
        catch (Exception e){
            Log.e(null, "runPoller: ", e);
        }
    }
    /**
     * @pre there is a user currently in the model
     */
    public boolean logout() {
        //checks to make sure there is a user to be logged out of the app
        if(model.getUser() == null){
            return false;
        }

        //creates new async task to perform the logout
        try{
            new LogoutAsyncTask().execute().get();
        }
        catch (Exception e){
            return false;
        }

        //checks to see if the user was logged out and removed from the model
        if(model.getUser() != null){
            return false;
        }
        else return true;
    }

    public List<BaseGameSummary> getGamesList() {
        return model.getGamesList();
    }

    private boolean getGamesFromServer(){
        return false;
    }

    /**
     * create a new game with a given name puts the player in the game with the given color
     * needs a Valid AuthToken so a user must have been signed in to the game
     */
    public boolean createGame(String gameName, SharedColor color) {
        CreateGameObject newGame = new CreateGameObject(gameName,color);

        //creates the game in question on the server
        try{
            new CreateGameAsyncTask().execute(newGame).get();
        }
        catch (Exception e){
            Log.e(null, "createGame: ",e );
            return false;
        }
        if(model.getGameToJoin() == null) {
            return false;
        }

        this.timer.cancel();
        //gets the updated game list
        try{
            new GetGameListAsyncTask().execute().get();
        }
        catch (Exception e){
            Log.e(null, "createGame: ",e );
        }
        String gameToJoin = model.getGameToJoin();
        this.startPollerTimer();


        //checks if the created game is in the new games list if it is not the function returns false
        BaseGameSummary createdGame = model.getGameByID(gameToJoin);
        if(createdGame == null){
            return false;
        }
        else{
            model.setGameToJoin(null);
            model.setCurrentGame(createdGame);
        }
        return true;
    }

    /**
     *
     * @param gameID id of the game to be joined
     *
     *
     * @pre gameID must valid and found in game list
     */
    public boolean joinGame(String gameID, SharedColor newPlayerColor) {
        if(model.getCurrentGame() != null){
            return false;
        }

        //creates the join game object to be passed ot the joingameasynctask
         JoinGameObject gameToJoin = new JoinGameObject(gameID,newPlayerColor);

        //calls asynctask to join game on the server
        try{
            new JoinGameAsyncTask().execute(gameToJoin).get();
        }
        catch (Exception e){
            Log.e(null, "joinGame: ",e );
            return false;
        }
        if(model.getCurrentGame() == null){
            return false;
        }
        else return true;


    }

    /**
     * @pre the model must have a current game
     *
     * @return returns a boolean to indicate whether or not the operation was successful
     */
    public boolean leaveGame() {
       if(model.getUser() == null || model.getCurrentGame() == null){
           return false;
       }

       try{
           new LeaveGameAsyncTask().execute().get();
       }
       catch (Exception e){
           return false;
       }
       if(model.getCurrentGame() != null){
           return false;
       }
       return true;
    }

    public void addObserver(Observer toAdd){
        model.addObserver(toAdd);
    }

    public void removeObserver(Observer toRemove){
        model.deleteObserver(toRemove);
    }
}
