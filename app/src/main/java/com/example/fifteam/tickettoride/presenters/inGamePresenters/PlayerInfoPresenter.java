package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.inGameViews.PlayerInfoFragment;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class PlayerInfoPresenter implements Observer {

    private PlayerInfoFragment view;

    public PlayerInfoPresenter(PlayerInfoFragment view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateAdapter();
    }

    public List<PlayerGameSummary> getPlayerInfo() {
        return ClientGamePresenterFacade.getInstance().getOtherPlayerGameList();
    }
}
