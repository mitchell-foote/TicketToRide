package com.example.fifteam.tickettoride.presenters;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.views.GamesListView;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by USER on 10/2/2017.
 */

public class GamesListPresenter implements Observer, Toaster{

    GamesListView view;

    public GamesListPresenter(GamesListView view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
    }


    public List<BaseGameSummary> getGamesList() {
        return ClientFacade.getInstance().getGamesList();
    }

    public void logout() {
        ClientFacade.getInstance().logout(this);
        view.logoutViewChange();
    }

    public void createGame(String name) {
        displayMessage("Creating game...");
        ClientFacade.getInstance().createGame(name, SharedColor.GREEN, this);
    }

    public void joinGame(String gameID, int playerCount) {
        displayMessage("Joining game...");
        ClientFacade.getInstance().joinGame(gameID, getAssignedPlayerColor(playerCount), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (ClientFacade.getInstance().getCurrGame() != null) {
            ClientFacade.getInstance().removeObserver(this);
            view.enterGameViewChange();
        }
        view.updateList();
    }

    @Override
    public void displayMessage(String message) {
        view.displayMessage(message);
    }

    private SharedColor getAssignedPlayerColor(int playerCount) {
        switch (playerCount) {
            case 1: return SharedColor.BLUE;
            case 2: return SharedColor.RED;
            case 3: return SharedColor.YELLOW;
            case 4: return SharedColor.BLACK;
        }
        return null;
    }
}
