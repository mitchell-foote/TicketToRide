package com.example.gameModel.PlayerGameSummaries;

import com.example.gameModel.classes.DestinationCard;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by samks on 10/24/2017.
 * Class used to store data about the user Specific to the client
 */

public class UserGameSummary extends PlayerGameSummary {
    private Map<SharedColor,Integer> hand;
    private List<DestinationCard> destinations;

    public UserGameSummary(Map<SharedColor, Integer> hand, List<DestinationCard> destinations) {
        this.hand = hand;
        this.destinations = destinations;
    }

    public UserGameSummary(String name, SharedColor color, int handSize, int points, int trainsRemaining,
                           int destinationHandSize, Map<SharedColor, Integer> hand,
                           List<DestinationCard> destinations) {
        super(name, color, handSize, points, trainsRemaining,destinationHandSize);
        this.hand = hand;
        this.destinations = destinations;
    }

    public  UserGameSummary(){
        this.hand = new HashMap<>();
        this.destinations = new ArrayList<>();
    }

    public Map<SharedColor, Integer> getHand() {

        return hand;
    }

    public void setHand(Map<SharedColor, Integer> hand) {
        this.hand = hand;
    }

    public List<DestinationCard> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationCard> destinations) {
        this.destinations = destinations;
    }

    public void addCard(SharedColor colorToAdd){
        this.incrementTrainHandSize();
        int toIncrement = this.hand.get(colorToAdd);
        toIncrement++;
        this.hand.put(colorToAdd,toIncrement);
    }

    public void addDestination(List<DestinationCard> toAdd){
        this.destinations.addAll(toAdd);
    }

    public void removeDestination(DestinationCard toRemove){
        this.destinations.remove(toRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGameSummary)) return false;

        UserGameSummary that = (UserGameSummary) o;

        if (getHand() != null ? !getHand().equals(that.getHand()) : that.getHand() != null)
            return false;
        return getDestinations() != null ? getDestinations().equals(that.getDestinations()) : that.getDestinations() == null;
    }

    @Override
    public int hashCode() {
        int result = getHand() != null ? getHand().hashCode() : 0;
        result = 31 * result + (getDestinations() != null ? getDestinations().hashCode() : 0);
        return result;
    }
}
