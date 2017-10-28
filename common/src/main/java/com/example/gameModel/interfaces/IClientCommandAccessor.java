package com.example.gameModel.interfaces;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public interface IClientCommandAccessor
{
    void drawTrainCard(String username, String cardId);

    void postMessage(String message, String username);
    void drawDestinationCard(String username, List<String> cardId);
    void returnDestinationCard(String username, String cardId);
    void nextTurn(String username);
    void addFaceUpTrainCard(String username, String cardId);
}
