package com.example.fifteam.tickettoride.model.FacadeStates;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/12/2017.
 */

public class NotYourTurnState implements FacadeState {

    public NotYourTurnState() {
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Not your turn, action invalid");
        }
    }
    public void returnDestCard(String cardId, Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Not your turn, action invalid");
        }

    }
    public void claimRoute(String routeId, SharedColor color,Toaster toaster){
        if(toaster != null) {
            toaster.displayMessage("Not your turn, action invalid");
        }
    }
    public boolean canDrawDestCard(){
        return false;
    }
    public boolean canDrawTrainCard(){
        return false;
    }
    public boolean canClaimRoute(){
        return false;
    }
}
