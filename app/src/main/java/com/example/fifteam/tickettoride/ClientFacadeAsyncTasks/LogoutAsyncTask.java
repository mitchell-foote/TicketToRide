package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/4/17.
 */

public class LogoutAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        ClientModel model = ClientModel.getInstance();
        //gets the user currently stored in the model
        User currentUser = model.getUser();

        //checks if the current user non null
        if (currentUser == null){
            return null;
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
            return null;
        }

        if (logoutBool){
            model.setCurrentGame(null);
            model.setGamesList(null);
            model.setUser(null);
        }

        //returns result of the logout attempt
        return null;
    }
}
