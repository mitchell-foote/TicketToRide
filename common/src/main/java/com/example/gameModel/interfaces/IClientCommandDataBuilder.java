package com.example.gameModel.interfaces;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.classes.PlayerScoreContainer;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public interface IClientCommandDataBuilder
{
    IClientCommandData drawTrainCard(String username, String cardId);
    IClientCommandData postMessage(String message, String username);
    IClientCommandData drawDestinationCard(String username, List<String> cardId);
    IClientCommandData returnDestinationCard(String username, String cardId);
    IClientCommandData nextTurn(String username);
    IClientCommandData addFaceUpTrainCard(String username, String cardId, String newCardId);
    IClientCommandData setupTrainCards(String[] cards);
    IClientCommandData swapTrainCards(String username, String oldTrainCar, String newTrainCard);
    IClientCommandData endGame(PlayerScoreContainer[] finalScores);
    IClientCommandData longestTrainSwitch(String username, String longestLength);
    IClientCommandData lastRound(String username);
    IClientCommandData claimRoute(String username, String routeId, SharedColor color);
}
