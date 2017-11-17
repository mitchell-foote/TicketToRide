package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.inGameViews.TrainCardsFragment;
import com.example.gameModel.classes.TrainCard;
import com.example.model.enums.SharedColor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class TrainCardsPresenter implements Observer, Toaster {

    private TrainCardsFragment view;

    public TrainCardsPresenter(TrainCardsFragment view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        view.updateCardImages(getCardColors());
    }

    public SharedColor[] getCardColors() {

        List<TrainCard> trainCards = ClientGamePresenterFacade.getInstance().getFaceUpTrainCards();
        SharedColor[] colors = new SharedColor[trainCards.size()];

        for (int i = 0; i < trainCards.size(); i++) {
            colors[i] = trainCards.get(i).getColor();
        }

        return colors;
    }

    public void drawCardFromDeck() {
        ClientGamePresenterFacade.getInstance().drawTrainCard(null, this);
    }

    public void drawFaceUpCard(int pos) {
        List<TrainCard> trainCards = ClientGamePresenterFacade.getInstance().getFaceUpTrainCards();
        String cardToDraw = trainCards.get(pos).getReferenceId();

        ClientGamePresenterFacade.getInstance().drawTrainCard(cardToDraw, this);
    }

    @Override
    public void displayMessage(String message) {
        view.displayMessage(message);
    }
}
