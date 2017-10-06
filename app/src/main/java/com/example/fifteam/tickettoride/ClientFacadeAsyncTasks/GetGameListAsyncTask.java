package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

import java.util.List;

/**
 * Created by sam on 10/4/17.
 */

public class GetGameListAsyncTask extends AsyncTask<Void,Void,Boolean> {
    protected Boolean doInBackground(Void... voids){

        ClientModel model = ClientModel.getInstance();
        User currUser = model.getUser();


        String authToken = currUser.getAuthToken();
        //creates the server proxy which will be used to contact the server
        ServerProxy serverProxy = new ServerProxy();

        List<BaseGameSummary> toReturn;
        try{
            toReturn = serverProxy.getGames(authToken);
        }
        catch (Exception e){
            Log.e(null , "doInBackground: ", e);
            return null;
        }

        if (toReturn != null){
            model.setGamesList(toReturn);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        new GetGameListAsyncTask().execute();
    }
}
