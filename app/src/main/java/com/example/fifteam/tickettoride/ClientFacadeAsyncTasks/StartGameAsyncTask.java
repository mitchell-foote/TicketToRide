package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

/**
 * Created by sam on 10/8/17.
 */

public class StartGameAsyncTask extends AsyncTask<Void, Void, StartGameAsyncTask.StartGameResult> {

    private Toaster toaster;

    public StartGameAsyncTask(Toaster toaster){
        this.toaster = toaster;
    }
    @Override
    protected StartGameResult doInBackground(Void... voids) {
        ClientModel model = ClientModel.getInstance();
        User currUser = model.getUser();
        BaseGameSummary currGame  = model.getCurrentGame();

        if(currGame == null || currUser == null){
            return new StartGameResult("No current Game or User", false);
        }

        ServerProxy proxy = new ServerProxy();

        String gameID = currGame.getId();
        String authToken = currUser.getAuthToken();


        boolean result;
        try{
           result = proxy.startGame(gameID,authToken);
        }
        catch (Exception e){
            return new StartGameResult(e.getMessage(), false);
        }
        if(!result){
            return new StartGameResult("Start Game unsuccessful",result);
        }
        else return new StartGameResult(null, true);
    }

    @Override
    protected void onPostExecute(StartGameResult startGameResult) {
        ClientModel model = ClientModel.getInstance();
        if(startGameResult.isValid()){
            BaseGameSummary currGame = model.getCurrentGame();
            currGame.setStarted(true);
        }
        else {
            this.toaster.displayMessage(startGameResult.getErrorMessage());
        }
    }

    protected class StartGameResult{
        private String errorMessage;
        private boolean valid;

        public StartGameResult(String errorMessage, boolean valid) {
            this.errorMessage = errorMessage;
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
