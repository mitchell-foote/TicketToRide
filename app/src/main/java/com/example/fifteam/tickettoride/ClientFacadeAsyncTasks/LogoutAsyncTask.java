package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sam on 10/4/17.
 */

public class LogoutAsyncTask extends AsyncTask<Void, Void, LogoutAsyncTask.LogoutResult> {

    Toaster toaster;

    public LogoutAsyncTask(Toaster toaster){
        this.toaster = toaster;
    }

    @Override
    protected LogoutResult doInBackground(Void... voids) {
        ClientModel model = ClientModel.getInstance();
        //gets the user currently stored in the model
        User currentUser = model.getUser();

        //checks if the current user non null
        if (currentUser == null){
            return new LogoutResult(false);
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
            return new LogoutResult(e.getMessage());
        }

        if (logoutBool){
            return new LogoutResult(true);
        }
        else{
            return new LogoutResult(false);
        }
    }

    @Override
    protected void onPostExecute(LogoutResult logoutResult) {
        ClientModel model = ClientModel.getInstance();
        if(logoutResult.isValid()){
            model.setUser(null);
            model.setCurrentGame(null);
            model.setGamesList(new LinkedList<BaseGameSummary>());
        }
    }

    public class LogoutResult{
        private String errorMessage;
        private boolean valid;

        public LogoutResult(String errorMessage){
            this.errorMessage = errorMessage;
            valid = false;
        }

        public LogoutResult(boolean valid){
            this.valid = valid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }
}
