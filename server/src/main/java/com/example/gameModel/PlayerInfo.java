package com.example.gameModel;

import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.classes.TrainLookupTable;
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
        System.out.println("Adding destination card to " + color.toString() + " player's hand. His hand contains: ");
        for (int i = 0; i < destinationCards.size(); i++) {
            System.out.println(destinationCards.get(i));
        }
    }

    public void removeDestinationCard(String cardId) {
        for (int i = 0; i < destinationCards.size(); i++) {
            if (destinationCards.get(i).equals(cardId)) {
                destinationCards.remove(i);
                System.out.println("Removed destination card: " + cardId + " from " + color.toString() + " player's hand. His hand contains: ");
                for (int j = 0; j < destinationCards.size(); j++) {
                    System.out.println(destinationCards.get(j));
                }
                return;
            }
        }
        System.out.println("Error: cardId not found in player's hand");
    }

    public void addTrainCard(String cardId) {
        trainCards.add(cardId);
        System.out.println("Adding train card to " + color.toString() + " player's hand. His hand contains: ");
        for (int i = 0; i < trainCards.size(); i++) {
            System.out.println(trainCards.get(i));
        }
    }

    public void removeTrainCard(String cardId) {
        for (int i = 0; i < trainCards.size(); i++) {
            if (trainCards.get(i).equals(cardId)) {
                trainCards.remove(i);
                System.out.println("Removed train card: " + cardId + " from " + color.toString() + " player's hand. His hand contains: ");
                for (int j = 0; j < trainCards.size(); j++) {
                    System.out.println(trainCards.get(j));
                }
                return;
            }
        }
        System.out.println("Error: cardId not found in player's hand");
    }


    public int getRemainingTrains() {
        return remainingTrains;
    }

    public void playTrains(int trains) {
        remainingTrains -= trains;
    }

    public boolean playCardsForRouteClaim(int routeLength, SharedColor cardsColor) {
        List<String> cardsToBeUsed = new ArrayList<>();
        List<String> rainbowReserve = new ArrayList<>();

        for (int i = 0; i < trainCards.size(); i++) {
            TrainCard card = TrainLookupTable.getCardById(trainCards.get(i));
            if (card.getColor().equals(cardsColor)) {
                cardsToBeUsed.add(trainCards.get(i));
            } else if (card.getColor().equals(SharedColor.RAINBOW)) {
                rainbowReserve.add(trainCards.get(i));
            }
        }
        cardsToBeUsed.addAll(rainbowReserve);

        if (cardsToBeUsed.size() < routeLength) {
            return false;
        } else {
            for (int i = 0; i < routeLength; i++) {
                trainCards.remove(0);
            }
            return true;
        }

    }

    public void addPoints(int newPoints) {
        points += newPoints;
    }
}
