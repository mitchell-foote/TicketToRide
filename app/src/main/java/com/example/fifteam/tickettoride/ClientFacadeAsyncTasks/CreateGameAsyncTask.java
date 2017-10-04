package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.model.CreateGameObject;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/4/17.
 */

public class CreateGameAsyncTask extends AsyncTask<CreateGameObject,Void,Void> {

    Toaster toaster;

    public CreateGameAsyncTask(Toaster toaster){
        this.toaster = toaster;
    }
    @Override
    protected Void doInBackground(CreateGameObject... createGameObjects) {
        //gets the clientModel
        ClientModel model = ClientModel.getInstance();
        // Retrieves current user to get the authToken stored within
        User currUser = model.getUser();

        //gets the object that stores the game name and user color out of the list of parameters
        CreateGameObject createGameObject = createGameObjects[0];

        //if currUser is null then the user was never set and there will be no authToken to
        if(currUser == null){
            return null;
        }
        //creates the ServerProxy which will be used to make a connection with the server
        ServerProxy proxy = new ServerProxy();


        String newGameID;
        //try catch block which attempts connection to the server
        try{
            newGameID = proxy.createGame(createGameObject.getGameName(),
                    createGameObject.getPlayerColor(), currUser.getAuthToken());
        }
        catch (Exception e){
            Log.e(null, "createGame: ",e );
            return null;
        }

        //checks to see if valid game id was returned
        if (newGameID == null || newGameID.equals("")){
            return null;
        }
        else{
            model.setGameToJoin(newGameID);
        }
        return null;
    }
}
