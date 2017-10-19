package com.example.gameModel.interfaces;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface ICardDeck
{
    void initDeck();
    ICard getCardById(String cardId);
    ICard drawRandomCard();
    void removeCardById(String cardId);
    Class<?> getICardClassType();
}
