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
    public TrainDeck(){initDeck();}

    public void initDeck() {
        DrawPile = TrainLookupTable.getIdStringSet();
        DiscardPile = new HashSet<>();
        FaceUpPile = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String cardId = getRandomCardId();
            FaceUpPile.add(cardId);
            removeCardById(cardId);
        }
    }

    public TrainCard drawRandomCard()
    {
        String id = getRandomCardId();
        TrainCard card = TrainLookupTable.getCardById(id);
        removeCardById(id);
        return card;
    }

    public TrainCard drawFaceUpCard(String cardId) {
        for (String s : FaceUpPile) {
            if (cardId.equals(s)) {
                return TrainLookupTable.getCardById(cardId);
            }
        }
        return null;
    }

    public void removeCardById(String cardId) {
        DrawPile.remove(cardId);
        DiscardPile.add(cardId);
    }

    public Class<?> getICardClassType()
    {
        return TrainCard.class;
    }

    private String getRandomCardId() {
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
}