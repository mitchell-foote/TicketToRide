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
        ClientFacade.getInstance().createGame(name, SharedColor.GREEN, this);
        displayMessage("Creating game...");
    }

    public void joinGame(String gameID) {
        ClientFacade.getInstance().joinGame(gameID, SharedColor.BLUE, this);
        displayMessage("Joining game...");
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateList();
        if (ClientFacade.getInstance().getCurrGame() != null) {
            ClientFacade.getInstance().removeObserver(this);
            view.enterGameViewChange();
        }
    }

    @Override
    public void displayMessage(String message) {
        view.displayMessage(message);
    }
}
