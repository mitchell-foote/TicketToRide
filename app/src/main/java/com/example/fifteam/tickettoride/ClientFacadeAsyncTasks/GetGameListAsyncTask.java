package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.Player;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sam on 10/4/17.
 */

public class GetGameListAsyncTask extends AsyncTask<Void,Void,List<BaseGameSummary>> {
    protected List<BaseGameSummary> doInBackground(Void... voids){

        SystemClock.sleep(2000);
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


        return toReturn;
    }

    @Override
    protected void onPostExecute(List<BaseGameSummary> newGameList) {
        ClientModel model = ClientModel.getInstance();

        if(model.isLoggedOut()){
            model.setLoggedOut(false);
            return;
        }
        if(newGameList != null){
            model = ClientModel.getInstance();
            model.setGamesList(newGameList);
        }
        if(model.isPollerContinue()) {
            new GetGameListAsyncTask().execute();
        }
    }
}
