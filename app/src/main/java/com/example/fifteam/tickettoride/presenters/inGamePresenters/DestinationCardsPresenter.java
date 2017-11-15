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

public class DestinationCardsPresenter implements Observer{

    //todo kc, change the way destination cards works for non initial turns in some way, I am open to suggestion
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
        List<DestinationCard> destinations = ClientGamePresenterFacade.getInstance().getFaceUpDestinationCards();
        if (destinations.size() == 3) {
            String destination0 = destinations.get(0).toString();
            String destination1 = destinations.get(1).toString();
            String destination2 = destinations.get(2).toString();

            view.setIsSelectingDestinations(true);
            view.setDestinationText(destination0, destination1, destination2);
        }
    }

    public List<DestinationCard> getMyDestinations() {
        return ClientGamePresenterFacade.getInstance().getUserDestinations();
    }

    public void selectDestinations(boolean checked0, boolean checked1, boolean checked2) {
        List<DestinationCard> destinations = ClientGamePresenterFacade.getInstance().getFaceUpDestinationCards();
        List<DestinationCard> toDiscard = new ArrayList<>();

        //destinations becomes a list of the cards we will discard, to suit the facade
        if (!checked2) {
            toDiscard.add(destinations.get(2));
            destinations.remove(2);
        }
        if (!checked1) {
            toDiscard.add(destinations.get(1));
            destinations.remove(1);
        }
        if (!checked0) {
            toDiscard.add(destinations.get(0));
            destinations.remove(0);
        }

        /*
        if (toDiscard.size() == 0) {
            ClientGamePresenterFacade.getInstance().discardDestCard(null, null);
        } else {
            for (DestinationCard destination : toDiscard) {
                ClientGamePresenterFacade.getInstance().discardDestCard(destination.getReferenceId(), null);
            }
        }
        */
        //todo kc i commented out your code and put a temporary implementation to accomodate the changes,
        // feel free to change whatever, I also as you can see didnt get rid of you old code, so feel free to do whatever
        List<String> listToDiscard = new ArrayList<>();
        for(DestinationCard card : toDiscard){
            listToDiscard.add(card.getReferenceId());
        }
        ClientGamePresenterFacade.getInstance().discardDestCard(listToDiscard,null);

        view.setIsSelectingDestinations(false);
        view.updateAdapter();
    }
}
