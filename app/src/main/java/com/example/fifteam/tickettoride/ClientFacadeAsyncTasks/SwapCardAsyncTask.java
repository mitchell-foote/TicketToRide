package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;

/**
 * Created by samks on 11/12/2017.
 */

public class SwapCardAsyncTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        ClientGameModel model = ClientGameModel.getInstance();

        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        String cardId = strings[0];

        GameServerProxy proxy = new GameServerProxy();
        proxy.addFaceUpTrainCard(authId,cardId,gameId);

        return null;
    }
}
