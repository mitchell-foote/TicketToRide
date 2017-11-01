package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.inGameViews.ChatFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class ChatPresenter implements Observer{

    ChatFragment view;

    public ChatPresenter(ChatFragment view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
