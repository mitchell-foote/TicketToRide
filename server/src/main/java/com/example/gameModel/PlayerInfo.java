package com.example.gameModel;

import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by ninjup on 10/22/17.
 */

public class PlayerInfo {

    private List<String> destinationCards;
    private List<String> trainCards;
    private int remainingTrains;
    private int points;
    private boolean hasLongestRoad;
    private SharedColor color;

    public PlayerInfo(SharedColor color) {
        this.color = color;
        points = 0;
        hasLongestRoad = false;
    }

    public void addDestinationCard(String cardId) {
        destinationCards.add(cardId);
    }

    public void removeDestinationCard(String cardId) {
        for (int i = 0; i < destinationCards.size(); i++) {
            if (destinationCards.get(i).equals(cardId)) {
                destinationCards.remove(i);
            }
        }
    }

    public void addTrainCard(String cardId) {
        trainCards.add(cardId);
    }

    public void removeTrainCard(String cardId) {
        for (int i = 0; i < trainCards.size(); i++) {
            if (trainCards.get(i).equals(cardId)) {
                trainCards.remove(i);
            }
        }
    }


}
