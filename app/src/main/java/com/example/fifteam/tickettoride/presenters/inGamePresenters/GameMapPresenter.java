package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.GameMapView;
import com.example.fifteam.tickettoride.views.inGameViews.DestinationCardsFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by USER on 11/1/2017.
 */

public class GameMapPresenter implements Observer {

    private GameMapView view;

    public GameMapPresenter(GameMapView view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
