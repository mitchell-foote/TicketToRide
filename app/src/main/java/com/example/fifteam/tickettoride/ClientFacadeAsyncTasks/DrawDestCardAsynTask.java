package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;

/**
 * Created by samks on 11/12/2017.
 */

public class DrawDestCardAsynTask extends AsyncTask<Void, Void, Void>{
    @Override
    protected Void doInBackground(Void... voids) {
        ClientGameModel model = ClientGameModel.getInstance();
        String authId = model.getAuthToken();
        String gameId = model.getGameID();

        GameServerProxy serverProxy = new GameServerProxy();
        serverProxy.drawDestinationCard(authId,gameId);
        return null;
    }
}
