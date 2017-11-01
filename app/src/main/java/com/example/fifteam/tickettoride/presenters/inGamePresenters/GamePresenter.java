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

public class GamePresenter implements Observer{

    private GameView view;

    public GamePresenter(GameView view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
        view.setUsername(ClientFacade.getInstance().getUser().getName());
    }


    @Override
    public void update(Observable o, Object arg) {
        int trains = ClientGamePresenterFacade.getInstance().getUserTrainCount();
        view.setTrainCount(trains);

        setHand(ClientGamePresenterFacade.getInstance().getUserHand());
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
}
