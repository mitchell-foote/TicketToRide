package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;

/**
 * Created by samks on 11/1/2017.
 */

public class ChatAsyncTask extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {

        GameServerProxy proxy = new GameServerProxy();
        ClientGameModel model = ClientGameModel.getInstance();

        model.setRunningAsync(true);
        SystemClock.sleep(750);

        String message = strings[0];
        String autId = model.getAuthToken();
        String gameId = model.getGameID();
        proxy.postMessage(message,autId,gameId);
        SystemClock.sleep(4000);

        model.setRunningAsync(false);
        return null;
    }
}
