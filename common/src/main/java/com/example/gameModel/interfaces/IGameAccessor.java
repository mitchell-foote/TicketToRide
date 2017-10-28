package com.example.gameModel.interfaces;

import com.example.gameCommunication.commands.interfaces.ICommandContainer;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface IGameAccessor
{
    String drawTrainCard(String authId, String gameId);
    List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId);
    void postMessage(String message, String authId, String gameId);
    String drawDestinationCard(String authId, String gameId);
    boolean returnDestinationCard(String authId, String cardId, String gameId);
    String endTurn(String authId, String gameId);
    String addFaceUpTrainCard(String authId, String cardId, String gameId);
}
