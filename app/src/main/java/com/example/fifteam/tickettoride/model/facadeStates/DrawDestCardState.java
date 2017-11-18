package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.DrawDestCardAsynTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ReturnDestinationCardAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by samks on 11/12/2017.
 */

public class DrawDestCardState implements FacadeState {
    private ClientGamePresenterFacade facade;
    public DrawDestCardState(ClientGamePresenterFacade facade) {
        this.facade = facade;
        if(!facade.isFirstTurn()) {
            new DrawDestCardAsynTask().execute();
        }
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("You chose draw TrainCard, action invalid");
        }
    }
    public void returnDestCard(List<String> cardIds, Toaster toaster){
        ClientGameModel model = ClientGameModel.getInstance();
        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        for(String s: cardIds){
            try {
                new ReturnDestinationCardAsyncTask().execute(authId, s, gameId).get();
            }
            catch (Exception e){
                toaster.displayMessage(e.getMessage());
            }
        }
        facade.endTurn();

    }
    public void claimRoute(String routeId,SharedColor color,Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("You chose draw TrainCard, action invalid");
        }
    }
    public boolean canDrawDestCard(){
        return true;
    }
    public boolean canDrawTrainCard(){
        return false;
    }
    public boolean canClaimRoute(){
        return false;
    }
}
