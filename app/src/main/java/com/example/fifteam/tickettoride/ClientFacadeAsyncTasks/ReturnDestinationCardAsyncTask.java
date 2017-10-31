package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;

/**
 * Created by samks on 10/30/2017.
 */

public class ReturnDestinationCardAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        GameServerProxy proxy = new GameServerProxy();
        String authId = strings[0];
        String cardId = strings[1];
        String gameId = strings[2];
        proxy.returnDestinationCard(authId,cardId,gameId);
        return null;
    }
}
