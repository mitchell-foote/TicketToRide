package com.example.gameModel.classes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class TrainDeck
{
    private Set<String> DrawPile;
    private Set<String> FaceUpPile;
    private Set<String> DiscardPile;

    public TrainDeck(){
        DrawPile = TrainLookupTable.getIdStringSet();
        DiscardPile = new HashSet<>();
        FaceUpPile = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String cardId = getRandomCardId();
            FaceUpPile.add(cardId);
            removeCardById(cardId);
        }

        if (hasThreeWilds()) {
            shuffleFaceUpPile();
        }
    }

    public String drawRandomCard() {
        String id = getRandomCardId();
        removeCardById(id);
        return id;
    }

    public String drawFaceUpCard(String cardId) {
        for (String s : FaceUpPile) {
            if (cardId.equals(s)) {
                FaceUpPile.remove(s);
                String replacementCard = drawRandomCard();
                FaceUpPile.add(replacementCard);

                return replacementCard;
            }
        }
        return null;
    }

    public void removeCardById(String cardId) {
        DrawPile.remove(cardId);

        if (DrawPile.size() == 0) {
            DrawPile = DiscardPile;
            DiscardPile = new HashSet<>();
        }
    }

    public void addCardToDiscardPile(String cardId) {
        DiscardPile.add(cardId);
    }

    public String getRandomCardId() {
        int index = new Random().nextInt(DrawPile.size());
        int i = 0;
        for (String s : DrawPile) {
            if (i == index) {
                return s;
            }
            i++;
        }
        return null;
    }

    public Set<String> getFaceUpPile() {
        return FaceUpPile;
    }

    public void shuffleFaceUpPile() {
        System.out.println("shuffling face up pile");
        for (String s : FaceUpPile) {
            DiscardPile.add(s);
        }
        FaceUpPile = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String cardId = getRandomCardId();
            FaceUpPile.add(cardId);
            removeCardById(cardId);
        }

        if (hasThreeWilds()) {
            shuffleFaceUpPile();
        }
    }

    public boolean hasThreeWilds() {
        int wildCards = 0;
        for (String s : FaceUpPile) {
            if (s.contains("loc")) {
                wildCards++;
            }
        }
        if (wildCards >= 3) {
            return true;
        }
        return false;
    }
}