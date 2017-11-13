package com.example.fifteam.tickettoride.model.facadeStates;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/12/2017.
 */

public interface FacadeState {
    void drawTrainCard(String cardId, Toaster toaster);
    void returnDestCard(String cardId, Toaster toaster);
    void claimRoute(String routeId, SharedColor color,Toaster toaster);
    boolean canDrawDestCard();
    boolean canDrawTrainCard();
    boolean canClaimRoute();
}
