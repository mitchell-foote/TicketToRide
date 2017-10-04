package com.example.fifteam.tickettoride.model;

import android.util.Log;
import android.util.MonthDisplayHelper;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.LoginAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.LogoutAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.RegisterAsyncTask;
import com.example.model.classes.login.BaseGameSummary;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * A façade for client-side interactions with the data stored in the model, so that other classes
 * need not bother themselves with any of how the model is structured internally, just asking for
 * the info they need and being given it.
 *
 * Created by kcwillmore on 9/28/17.
 */
public class ClientFacade {

    private static ClientFacade ourInstance = new ClientFacade();
    private ClientModel model = ClientModel.getInstance();


    public static ClientFacade getInstance(){
        return ourInstance;
    }

    private ClientFacade(){}
    /**
     * unsure if we want error checking here, for now I will leave it without but I assume we will want it going forward
     * Sam
     *
     */
    public boolean login(String username, String password) {

        try {
            new LoginAsyncTask().execute(username, password).get();
        }
        catch (Exception e){
            //Log.e(null, "login: ",e);
            System.out.println(e);
            return false;
        }

        if(model.getUser() == null){
            return false;
        }
        else return true;
    }

    /**
     *
     * @param username username of the user to be registerd
     * @param password password of the user to be registered
     */
    public boolean register(String username, String password) {
       try{
           new RegisterAsyncTask().execute(username,password);
       }
       catch (Exception e){
           return false;
       }

       if(model.getUser() == null) {
           return false;
       }
       else return true;
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


    /**
     * create a new game with a given name puts the player in the game with the given color
     * needs a Valid AuthToken so a user must have been signed in to the game
     */
    public boolean createGame(String gameName, SharedColor color) {
        // Retrieves current user to get the authToken stored within
        User currUser = model.getUser();

        //if currUser is null then the user was never set and there will be no authToken to
        if(currUser == null){
            return false;
        }
        //creates the ServerProxy which will be used to make a connection with the server
        ServerProxy proxy = new ServerProxy();


        String newGameID;
        //try catch block which attempts connection to the server
        try{
            newGameID = proxy.createGame(gameName,color,currUser.getAuthToken());
        }
        catch (Exception e){
            Log.e(null, "createGame: ",e );
            return false;
        }

        //checks to see if valid game id was returned
        if (newGameID == null || newGameID.equals("")){
            return false;
        }
        //// TODO: 10/1/17 figure out create game sequence 
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
        User currUser = model.getUser();
        //checks to see if a game with the associated gameID is found in the model
        BaseGameSummary gameToJoin = model.getGameByID(gameID);

        if(gameToJoin == null){
            return false;
        }

        //creates the ServerProxy to be used to contact the server
        ServerProxy serverProxy = new ServerProxy();

        boolean joinSuccessBool = false;
        //try-catch block which attempts to join the game
        try{
            joinSuccessBool = serverProxy.joinGame(gameID,newPlayerColor,currUser.getAuthToken());
        }
        catch (Exception e){
            Log.e(null, "joinGame: ", e);
        }

        //if the attempt to join the game was successful the current game in the model is set to
        // the game in question and then returns whether or not the attempt to join the game was successful

        if(joinSuccessBool){
            model.setCurrentGame(gameToJoin);
        }
        return  joinSuccessBool;


    }

    /**
     * @pre the model must have a current game
     *
     * @return returns a boolean to indicate whether or not the operation was successful
     */
    public boolean leaveGame() {
        //retrieve the current user and current game stored in the model
        BaseGameSummary currGame = model.getCurrentGame();
        User currUser = model.getUser();


        //if the curr user or currGame are null then the user cannot leave the game and an error has occured
        if(currUser == null || currGame == null){
            return false;
        }

        //creates the server proxy used to contact the server and the boolean which will store the
        //success or failure of the leave game operation
        ServerProxy serverProxy = new ServerProxy();

        boolean leaveGameBool;


        //try catch block which attempts to carry out the leaveGame operation on the server
        try{
            leaveGameBool = serverProxy.leaveGame(currGame.getId(),currUser.getAuthToken());
        }
        catch (Exception e){
            Log.e(null, "leaveGame: ",e);
            return false;
        }

        //checks if the leaveGame operation was successful, if it was the currGame in the model is
        // set to null, the method then returns the success or failure of the leaveGame operation
        if(leaveGameBool){
           model.setCurrentGame(null);
        }
        return leaveGameBool;

    }

    public static void main(String[] args){
        ClientFacade test = ClientFacade.getInstance();
        test.login("user","pass");
    }
}
