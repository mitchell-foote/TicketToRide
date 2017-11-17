package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.inGameViews.DestinationCardsFragment;
import com.example.gameModel.classes.DestinationCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class DestinationCardsPresenter implements Observer {

    private DestinationCardsFragment view;

    public DestinationCardsPresenter(DestinationCardsFragment view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        checkForDestinations();
        view.updateAdapter();
    }

    public void checkForDestinations() {
        if (ClientGamePresenterFacade.getInstance().isUserTurn()) {
            List<DestinationCard> destinations = ClientGamePresenterFacade.getInstance().getFaceUpDestinationCards();
            if (destinations.size() == 3) {
                String destination0 = destinations.get(0).toString();
                String destination1 = destinations.get(1).toString();
                String destination2 = destinations.get(2).toString();

                view.setIsSelectingDestinations(true);
                view.setDestinationText(destination0, destination1, destination2);
            }
        } else {
            view.setIsSelectingDestinations(false);
        }
    }

    public List<DestinationCard> getMyDestinations() {
        return ClientGamePresenterFacade.getInstance().getUserDestinations();
    }

    public void selectDestinations(boolean checked0, boolean checked1, boolean checked2) {
        List<DestinationCard> destinations = ClientGamePresenterFacade.getInstance().getFaceUpDestinationCards();
        List<String> toDiscard = new ArrayList<>();

        //construct a list of the cards we will discard, to suit the facade
        if (!checked0) {
            toDiscard.add(destinations.get(0).getReferenceId());
        }
        if (!checked1) {
            toDiscard.add(destinations.get(1).getReferenceId());
        }
        if (!checked2) {
            toDiscard.add(destinations.get(2).getReferenceId());
        }

        ClientGamePresenterFacade.getInstance().discardDestCard(toDiscard, null);

        view.setIsSelectingDestinations(false);
        view.updateAdapter();
    }

    public int minRequiredDestinations() {
        if (ClientGamePresenterFacade.getInstance().isFirstTurn()) {
            return 2;
        } else {
            return 1;
        }
    }
}
