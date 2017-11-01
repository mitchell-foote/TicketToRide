package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.inGameViews.PlayerInfoFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class PlayerInfoPresenter implements Observer {

    private PlayerInfoFragment view;

    public PlayerInfoPresenter(PlayerInfoFragment view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
