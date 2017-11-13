package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.DrawCardAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.SwapCardAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.classes.TrainLookupTable;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/12/2017.
 */

public class DrawTrainCardState implements FacadeState {
    private ClientGamePresenterFacade facade;
    private int remainingCards;
    public DrawTrainCardState(ClientGamePresenterFacade facade) {
        this.facade = facade;
        this.remainingCards = 2;
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        TrainCard toDraw = TrainLookupTable.getCardById(cardId);
        if(cardId == null){
            this.remainingCards -= 2;
            new DrawCardAsyncTask().execute();
        }
        else if(toDraw.getColor() == SharedColor.RAINBOW){
            this.remainingCards -= 2;
            new SwapCardAsyncTask().execute(cardId);
        }
        else{
            this.remainingCards -= 1;
            new SwapCardAsyncTask().execute(cardId);
        }
        if(remainingCards == 0){
            facade.endTurn();
        }
    }
    public void returnDestCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Invalid choice, Train Card Turn");
        }

    }
    public void claimRoute(String routeId, SharedColor color, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Invalid choice, Train Card Turn");
        }
    }
    public boolean canDrawDestCard(){
        return false;
    }
    public boolean canDrawTrainCard(){
        return true;
    }
    public boolean canClaimRoute(){
        return false;
    }
}
