package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;
import com.example.gameModel.interfaces.ICardDeck;

import java.util.Set;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class TrainDeck implements ICardDeck
{
    private Set<String> DrawPile;
    private Set<String> FaceUpPile;
    private Set<String> DiscardPile;
    public TrainDeck(){initDeck();}

    @Override
    public void initDeck()
    {

    }

    @Override
    public ICard getCardById(String cardId)
    {
        return null;
    }

    @Override
    public ICard drawRandomCard()
    {
        return null;
    }

    @Override
    public void removeCardById(String cardId)
    {

    }

    @Override
    public Class<?> getICardClassType()
    {
        return TrainCard.class;
    }
}
