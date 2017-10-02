package com.example.fifteam.tickettoride.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;

import java.util.List;


/**
 * Created by sam on 10/1/17.
 */

public class PollerAsyncTask extends AsyncTask<String, Long, List<BaseGameSummary>>{

    protected List<BaseGameSummary> doInBackground(String... autToken){



        String authToken = autToken[0];
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
        return toReturn;

    }

    private void testFunction(){
        System.out.println("timer executed");
    }

    protected void onPostExecute(List<BaseGameSummary> gameList){
        ClientModel clientModel = ClientModel.getInstance();

        testFunction();
        if(gameList != null) {
            clientModel.setGamesList(gameList);
        }
    }
}
