package com.example.gameModel;

import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninjup on 10/22/17.
 */

public class PlayerInfo {

    private List<String> destinationCards = new ArrayList<>();
    private List<String> trainCards = new ArrayList<>();

    private int remainingTrains = 45;
    private int points = 0;
    private boolean hasLongestRoad = false;
    private SharedColor color;

    public PlayerInfo(SharedColor color) {
        this.color = color;
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
