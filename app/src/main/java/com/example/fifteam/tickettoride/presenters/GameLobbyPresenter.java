package com.example.fifteam.tickettoride.presenters;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.GameLobbyView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by USER on 10/2/2017.
 */

public class GameLobbyPresenter implements Observer, Toaster{

    GameLobbyView view;

    public GameLobbyPresenter(GameLobbyView gameLobbyView) {
        this.view = gameLobbyView;
        ClientFacade.getInstance().addObserver(this);
    }

    public void getPlayerList() {

    }

    public void leaveGame() {
        ClientFacade.getInstance().leaveGame(this);
        view.leaveGameViewChange();
    }

    @Override
    public void displayMessage(String message) {
        view.displayMessage(message);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public boolean canStart() {
        return ClientFacade.getInstance().getNumberOfPlayersInCurrentGame() > 1;
    }

    public List<String> getPlayerNames() {
        return ClientFacade.getInstance().getPlayerNames();
    }
}
