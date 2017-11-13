package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;

import com.example.fifteam.tickettoride.model.ClaimRouteParamObj;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;
import com.example.model.enums.SharedColor;

import java.util.Observer;

/**
 * Created by samks on 11/12/2017.
 */

public class ClaimRouteAsycTask extends AsyncTask<ClaimRouteParamObj, Void, Void> {

    @Override
    protected Void doInBackground(ClaimRouteParamObj... objects) {
        ClientGameModel model = ClientGameModel.getInstance();

        ClaimRouteParamObj params = objects[0];

        String routeId = params.getRouteID();
        SharedColor color = params.getColor();
        String authId = model.getAuthToken();
        String gameId = model.getGameID();

        GameServerProxy proxy = new GameServerProxy();

        proxy.claimRoute(authId,gameId,routeId,color);
        return null;
    }
}
