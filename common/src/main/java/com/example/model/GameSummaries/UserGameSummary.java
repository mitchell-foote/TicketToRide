package com.example.model.GameSummaries;

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
    private List<String> destinations;

    public UserGameSummary(Map<SharedColor, Integer> hand, List<String> destinations) {
        this.hand = hand;
        this.destinations = destinations;
    }

    public UserGameSummary(String name, SharedColor color, int handSize, int points, int trainsRemaining, Map<SharedColor, Integer> hand, List<String> destinations) {
        super(name, color, handSize, points, trainsRemaining);
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

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public void addCard(SharedColor colorToAdd){

        int toIncrement = this.hand.get(colorToAdd);

        toIncrement++;

        this.hand.put(colorToAdd,toIncrement);
    }

    public void addDestination(String toAdd){
        this.destinations.add(toAdd);
    }

    public void removeDestination(String toAdd){
        this.destinations.remove(toAdd);
    }
}
