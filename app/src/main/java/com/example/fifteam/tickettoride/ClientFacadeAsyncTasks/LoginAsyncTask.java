package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/2/17.
 */

public class LoginAsyncTask extends AsyncTask<String,String,Void>{


    @Override
    protected Void doInBackground(String... strings){
        //gets an instance of the model
        ClientModel model = ClientModel.getInstance();
        // Creates ServerProxy to be used to contact the server
        ServerProxy proxy = new ServerProxy();

        //gets the username and password out of the params
        String username = strings[0];
        String password = strings[1];

        String authToken;
        //try-catch block which attempts to log user into the server
        try {
            authToken = proxy.login(username, password);
        }
        catch (Exception e){
            //logs exception, haven't determined if we want different error handling to take place
            Log.e(null, "login: ", e);
            return null;
        }

        //checks the returned authToken to make sure that it is not null or empty
        if(authToken == null || authToken.equals("")){
            Log.e(null, "login: ", new Exception("invalid authToken"));
            return null;
        }

        //creates new user to be added to the model
        User currentUser = new User(username,authToken,password);

        //sets the current user in the model saving the returned authToken
        model.setUser(currentUser);
        return null;
    }

}
