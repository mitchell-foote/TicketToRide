package com.example.gameModel.interfaces;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.classes.PlayerScoreContainer;

import java.util.List;

import javafx.util.Pair;

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
    void addFaceUpTrainCard(String username, String cardId, String newCardId);
    void setCommandHash(String newHash);
    void setupTrainCards(String[] cards);
    void swapTrainCards(String username, String oldTrainCard, String newTrainCard);
    void claimRoute(String username, String routeId);
    void endGame(PlayerScoreContainer[] scores);
    void longestTrainRouteSwitch(String username, String longestLength);
    void lastRound(String username);
}
