package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.inGameViews.DestinationCardsFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class DestinationCardsPresenter implements Observer{

    DestinationCardsFragment view;

    public DestinationCardsPresenter(DestinationCardsFragment view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
