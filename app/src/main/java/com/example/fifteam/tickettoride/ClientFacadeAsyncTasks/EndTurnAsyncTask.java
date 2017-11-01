package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;

/**
 * Created by samks on 11/1/2017.
 */

public class EndTurnAsyncTask extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        GameServerProxy proxy = new GameServerProxy();
        String authToken = strings[0];
        String gameId = strings[1];
        proxy.endTurn(authToken,gameId);
        return null;
    }
}
