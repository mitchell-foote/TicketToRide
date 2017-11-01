package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.inGameViews.GameView;
import com.example.model.classes.users.Player;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class GamePresenter implements Observer{

    GameView view;

    public GamePresenter(GameView view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
        view.setUsername(ClientFacade.getInstance().getUser().getName());
    }


    @Override
    public void update(Observable o, Object arg) {
        view.setCounts();
    }
}
