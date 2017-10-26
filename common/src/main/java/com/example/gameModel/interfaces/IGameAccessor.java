package com.example.gameModel.interfaces;

import com.example.gameCommunication.commands.interfaces.ICommandContainer;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface IGameAccessor
{
    ICommandContainer drawTrainCard(String authId, String gameId);
    List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId);
    ICommandContainer postMessage(String message, String authId, String gameId);
    ICommandContainer drawDestinationCard(String authId, String gameId);
    ICommandContainer returnDestinationCard(String authId, String cardId, String gameId);
    ICommandContainer endTurn(String authId, String gameId);
    ICommandContainer addFaceUpTrainCard(String authId, String cardId, String gameId);
}
