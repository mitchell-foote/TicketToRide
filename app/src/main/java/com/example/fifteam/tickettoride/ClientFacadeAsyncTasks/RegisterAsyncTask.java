package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/4/17.
 */

public class RegisterAsyncTask extends AsyncTask<String, String, Boolean> {

    @Override
    protected Boolean doInBackground(String... strings) {
        ClientModel model = ClientModel.getInstance();
        //creates the server proxy to be used to connect to the server
        ServerProxy serverProxy = new ServerProxy();

        //string which will hold the returned authToken
        String authToken;

        //sets username and password to be used to register new user
        String username = strings[0];
        String password = strings[1];

        //try-catch block which will attempt to make connection to the server
        try{
            authToken = serverProxy.register(username,password);
        }
        catch (Exception e){
            Log.e(null, "register: ", e);
            return false;
        }

        //if statement to check if authToken which was returned is valid
        if (authToken == null || authToken.equals("")){
            Log.e(null, "register: ", new Exception("invalid authToken returned"));
            return false;
        }

        //creates new user to be added to the model
        User currentUser = new User(username,authToken,password);

        //sets the newly registered user as the current user in the model
        model.setUser(currentUser);
        return true;
    }
}
