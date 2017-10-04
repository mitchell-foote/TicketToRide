package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.model.JoinGameObject;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

/**
 * Created by sam on 10/4/17.
 */

public class JoinGameAsyncTask extends AsyncTask<JoinGameObject,Void,Void> {

    @Override
    protected Void doInBackground(JoinGameObject... joinGameObjects) {
        ClientModel model = ClientModel.getInstance();

        User currUser = model.getUser();

        //gets the gameID and user color which will be used to join the game
        JoinGameObject gameObject = joinGameObjects[0];
        String gameID = gameObject.getGameId();
        SharedColor newPlayerColor = gameObject.getColor();
        //checks to see if a game with the associated gameID is found in the model
        BaseGameSummary gameToJoin = model.getGameByID(gameID);

        if(gameToJoin == null){
            return null;
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
        return null;
    }
}
