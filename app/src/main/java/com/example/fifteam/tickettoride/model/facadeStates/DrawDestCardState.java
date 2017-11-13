package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.DrawDestCardAsynTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ReturnDestinationCardAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/12/2017.
 */

public class DrawDestCardState implements FacadeState {
    private ClientGamePresenterFacade facade;
    public DrawDestCardState(ClientGamePresenterFacade facade) {
        this.facade = facade;
        new DrawDestCardAsynTask().execute();
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("You chose draw TrainCard, action invalid");
        }
    }
    public void returnDestCard(String cardId, Toaster toaster){
        ClientGameModel model = ClientGameModel.getInstance();
        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        if(cardId != null) {
            new ReturnDestinationCardAsyncTask().execute(authId, cardId, gameId);
        }
        else{
            facade.endTurn();
        }
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
