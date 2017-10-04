package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/4/17.
 */

public class LeaveGameAsyncTask extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        ClientModel model = ClientModel.getInstance();
        //retrieve the current user and current game stored in the model
        BaseGameSummary currGame = model.getCurrentGame();
        User currUser = model.getUser();


        //if the curr user or currGame are null then the user cannot leave the game and an error has occured
        if(currUser == null || currGame == null){
            return null;
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
            return null;
        }

        //checks if the leaveGame operation was successful, if it was the currGame in the model is
        // set to null, the method then returns the success or failure of the leaveGame operation
        if(leaveGameBool){
            model.setCurrentGame(null);
        }
        return null;

    }
}
