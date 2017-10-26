package com.example.gameModel.classes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DestinationDeck
{
    private Set<String> DrawPile;
    private Set<String> DiscardPile;

    public DestinationDeck() {
        initDeck();
    }

    public void initDeck() {
        DrawPile = DestinationLookupTable.getIdStringSet();
        DiscardPile = new HashSet<>();
    }

    public String drawRandomCard() {
        int index = new Random().nextInt(DrawPile.size());
        int i = 0;
        for (String s : DrawPile) {
            if (i == index) {
                removeCardById(s);
                return s;
            }
            i++;
        }
        return null;
    }

    //There is no real "discard pile," as cards are supposed to be added to the bottom of the DrawPile.
    //So, when the DrawPile is empty, we just re-add all the "discarded" cards. Players will have no idea.
    public void removeCardById(String cardId) {
        DrawPile.remove(cardId);
        if (DrawPile.isEmpty()) {
            DrawPile = DiscardPile;
            DiscardPile = new HashSet<>();
        }
    }

    public void returnCard(String cardId) {
        DiscardPile.add(cardId);
    }

}
