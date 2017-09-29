package com.example.fifteam.tickettoride.model;

import android.util.Log;
import android.util.MonthDisplayHelper;

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

    private static ClientModel model = ClientModel.getInstance();


    /**
     * unsure if we want error checking here, for now I will leave it without but I assume we will want it going forward
     * Sam
     *
     */
    public void login(String username, String password) {
        // Creates ServerProxy to be used to contact the server
        ServerProxy proxy = new ServerProxy();

        String authToken;
        //try-catch block which attempts to log user into the server
        try {
            authToken = proxy.login(username, password);
        }
        catch (Exception e){
            //logs exception, haven't determined if we want different error handling to take place
            Log.e(null, "login: ", e);
            return;
        }

        //checks the returned authToken to make sure that it is not null or empty
        if(authToken == null || authToken.equals("")){
            Log.e(null, "login: ", new Exception("invalid authtoken"));
            return;
        }

        //creates new user to be added to the model
        User currentUser = new User(username,authToken,password);

        //sets the current user in the model saving the returned authToken
        ClientModel.getInstance().setUser(currentUser);

    }

    /**
     *
     * @param username username of the user to be registerd
     * @param password password of the user to be registered
     */
    public void register(String username, String password) {
        //creates the server proxy to be used to connect to the server
        ServerProxy serverProxy = new ServerProxy();

        //string which will hold the returned authToken
        String authToken = null;

        //try-catch block which will attempt to make connection to the server
        try{
            authToken = serverProxy.register(username,password);
        }
        catch (Exception e){
            Log.e(null, "register: ", e);
        }

        //if statement to check if authToken which was returned is valid
        if (authToken == null || authToken.equals("")){
            Log.e(null, "register: ", new Exception("invalid authToken returned"));
        }

        //creates new user to be added to the model
        User currentUser = new User(username,authToken,password);

        //sets the newly registered user as the current user in the model
        ClientModel.getInstance().setUser(currentUser);
    }

    /**
     * @pre there is a user currently in the model
     */
    public boolean logout() {
        //gets the user currently stored in the model
        User currentUser = ClientModel.getInstance().getUser();

        //checks if the current user non null
        if (currentUser == null){
            return false;
        }

        //create ServerProxy object which will log out
        ServerProxy serverProxy = new ServerProxy();

        boolean logoutBool;
        //try-catch block which attempts to log the user out of the server
        try{
            logoutBool = serverProxy.logoff(currentUser.getAuthToken());
        }
        catch (Exception e){
            Log.e(null, "logout: ",e );
            return false;
        }

        //returns result of the logout attempt
        return logoutBool;
    }

    public List<BaseGameSummary> getGamesList() {
        return model.getGamesList();
    }


    /**
     *
     */
    public void createGame() {
        //TODO: create createGame method on ServerProxy
    }

    /**
     *
     * @param gameID id of the game to be joined
     *
     *
     * @pre gameID must valid and found in game list
     */
    public boolean joinGame(String gameID, SharedColor newPlayerColor) {
        User currUser = ClientModel.getInstance().getUser();
        //checks to see if a game with the associated gameID is found in the model
        BaseGameSummary gameToJoin = ClientModel.getInstance().getGameByID(gameID);

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
            ClientModel.getInstance().setCurrentGame(gameToJoin);
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
        BaseGameSummary currGame = ClientModel.getInstance().getCurrentGame();
        User currUser = ClientModel.getInstance().getUser();


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
            ClientModel.getInstance().setCurrentGame(null);
        }
        return leaveGameBool;

    }
}
