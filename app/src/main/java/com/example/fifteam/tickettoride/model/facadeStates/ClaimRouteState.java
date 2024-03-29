package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ClaimRouteAsycTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClaimRouteParamObj;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by samks on 11/12/2017.
 */

public class ClaimRouteState implements FacadeState {
    private ClientGamePresenterFacade facade;

    public ClaimRouteState(ClientGamePresenterFacade facade) {
        this.facade = facade;
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Invalid choice, claim route turn");
        }
    }
    public void returnDestCard(List<String> cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Invalid choice, claim route turn");
        }

    }
    public void claimRoute(String routeId, SharedColor color,Toaster toaster){
        ClientGameModel model = ClientGameModel.getInstance();
        String message = model.canClaimRoute(routeId,color);
        if(message != null){
            toaster.displayMessage(message);
            return;
        }
        ClaimRouteParamObj params = new ClaimRouteParamObj(routeId,color);
        new ClaimRouteAsycTask().execute(params);
        facade.endTurn();

    }
    public boolean canDrawDestCard(){
        return false;
    }
    public boolean canDrawTrainCard(){
        return false;
    }
    public boolean canClaimRoute(){
        return true;
    }
}
