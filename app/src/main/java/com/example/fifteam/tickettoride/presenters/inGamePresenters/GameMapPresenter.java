package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.GameMapView;
import com.example.gameModel.classes.Route;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jared Haviland on 11/1/2017.
 *
 * The GameMapPresenter class is the presenter for the GameMapView. When the view gets user input, it is passed to the presenter.
 * The presenter then communicates with the model. The presenter is also an observer of the model.  The model calls the ServerProxy
 * so that it can be updated, and when it is updated, the presenter is notified. Upon being notified, it tells the view to change.
 *
 */

public class GameMapPresenter implements Observer {

    private GameMapView view;

    /**
     * Initializes the presenter by assigning the view and making itself an observer of the model.
     *
     * @param view The GameMapView that this presenter communicates with. When the model is updated and changes need to take
     *             place on the map, the presenter tells this view to change.
     *
     * @pre view != null
     * @post The model now has this presenter as an observer.
     *
     */
    public GameMapPresenter(GameMapView view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    /**
     * Signals the model to add points to the current player's score.
     *
     * @param score The number of points to be added.
     *
     * @pre score >= 0
     * @post Current player's score += score
     */
    public void addToPlayerScore(int score) {
        ClientGamePresenterFacade.getInstance().updatePlayerPoints(score);
    }

    /**
     * Signals the model to subtract train cars from the current player's total.
     *
     * @param trains The number of train cars to subtract.
     *
     * @pre trains >= 0
     * @post Current player's train count -= trains
     */
    public void subtractPlayerTrains(int trains) {
        ClientGamePresenterFacade.getInstance().updatePlayerTrains(trains);
    }

    /**
     * Gets the current list of routes from the model.
     *
     * @pre None
     * @post Returns a list of zero or more Route objects.
     *
     * @return The current List of Route objects stored in the model.
     */
    public List<Route> getRouteList() {
        return ClientGamePresenterFacade.getInstance().getRouteList();
    }

    /**
     * The update method is called when the ClientGameModel changes, because this presenter is an Observer of the ClientGameModel.
     * This method gets a list of all of the currently claimed routes from the model and then tells the view to display that they
     * are claimed.
     *
     * @param o The observable object.
     * @param arg An argument passed to the notifyObservers method.
     *
     * @pre None
     * @post The view will have displayed all of the routes that have been claimed.
     */
    @Override
    public void update(Observable o, Object arg) {
        List<Route> claimedList = ClientGamePresenterFacade.getInstance().getClaimedRoutes();
        for (Route r : claimedList) {
            view.claimRoute(r);
        }
    }
}
