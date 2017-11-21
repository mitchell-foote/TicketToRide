package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.inGameViews.GameView;
import com.example.model.enums.SharedColor;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class GamePresenter implements Observer {

    private GameView view;

    public GamePresenter(GameView view) {
        this.view = view;

        ClientGamePresenterFacade.getInstance().addObserver(this);

        view.setUsername(ClientFacade.getInstance().getUser().getName());
        view.setToolbarColor(getUserColor());

        updateInfo();
    }


    @Override
    public void update(Observable o, Object arg) {

        //this being true indicates that the turn choice dialog should be displayed
        if (ClientGamePresenterFacade.getInstance().getPickTurnChoice() == true) {
            if (!ClientGamePresenterFacade.getInstance().isTurnChoiceDialogCurrentlyDisplayed()) {
                view.showTurnChoiceDialog();
                ClientGamePresenterFacade.getInstance().setTurnChoiceDialogCurrentlyDisplayed(true);
            }
        }
        updateInfo();
        if (ClientGamePresenterFacade.getInstance().isGameEnd()) {
            view.showEndGameDialog();
        }
    }

    public void updateInfo() {
        int trains = ClientGamePresenterFacade.getInstance().getUserTrainCount();
        view.setTrainCount(trains);

        int points = ClientGamePresenterFacade.getInstance().getUserPoints();
        view.setPoints(points);

        setHand(ClientGamePresenterFacade.getInstance().getUserHand());

        //todo: once an easier way of figuring out if you have longest route is made, make this work
        view.setHasLongestRoute(false);
    }

    private void setHand(Map<SharedColor, Integer> hand) {
        int black = -1;
        int blue = -1;
        int green = -1;
        int orange = -1;
        int purple = -1;
        int red = -1;
        int white = -1;
        int yellow = -1;
        int rainbow = -1;

        for (Map.Entry<SharedColor, Integer> cardEntry : hand.entrySet()) {
            switch (cardEntry.getKey()) {
                case RAINBOW:
                    rainbow = cardEntry.getValue();
                    break;
                case RED:
                    red = cardEntry.getValue();
                    break;
                case ORANGE:
                    orange = cardEntry.getValue();
                    break;
                case YELLOW:
                    yellow = cardEntry.getValue();
                    break;
                case GREEN:
                    green = cardEntry.getValue();
                    break;
                case BLUE:
                    blue = cardEntry.getValue();
                    break;
                case PURPLE:
                    purple = cardEntry.getValue();
                    break;
                case BLACK:
                    black = cardEntry.getValue();
                    break;
                case WHITE:
                    white = cardEntry.getValue();
                    break;
            }
        }
        view.setHand(black, blue, green, orange, purple, red, white, yellow, rainbow);
    }

    public int getUserColor() {
        SharedColor color = ClientGamePresenterFacade.getInstance().getUserGameSummary().getColor();
        return SharedColor.sharedColorToHex(color);
    }
}
