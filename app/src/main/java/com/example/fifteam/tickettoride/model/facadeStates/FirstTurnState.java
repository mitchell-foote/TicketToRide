package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.DrawDestCardAsynTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ReturnDestinationCardAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by kcwillmore on 11/17/17.
 */

public class FirstTurnState implements FacadeState {

    private ClientGamePresenterFacade facade;
    public FirstTurnState(ClientGamePresenterFacade facade) {
        this.facade = facade;
    }

    @Override
    public void drawTrainCard(String cardId, Toaster toaster) {
        if (toaster != null) {
            toaster.displayMessage("Not your turn, action invalid");
        }
    }

    @Override
    public void returnDestCard(List<String> cardId, Toaster toaster) { ClientGameModel model = ClientGameModel.getInstance();
        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        for(String s: cardId){
            try {
                new ReturnDestinationCardAsyncTask().execute(authId, s, gameId).get();
            }
            catch (Exception e){
                toaster.displayMessage(e.getMessage());
            }
        }
        facade.endTurn();
    }

    @Override
    public void claimRoute(String routeId, SharedColor color, Toaster toaster) {
        if (toaster != null) {
            toaster.displayMessage("Not your turn, action invalid");
        }
    }

    @Override
    public boolean canDrawDestCard() {
        return false;
    }

    @Override
    public boolean canDrawTrainCard() {
        return false;
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }
}
