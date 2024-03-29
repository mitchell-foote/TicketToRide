package com.example.gameModel.interfaces;

import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface IGameAccessor
{
    String drawTrainCard(String authId, String gameId);
    List<CommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId);
    void postMessage(String message, String authId, String gameId);
    String drawDestinationCard(String authId, String gameId);
    boolean returnDestinationCard(String authId, String cardId, String gameId);
    String endTurn(String authId, String gameId);
    String addFaceUpTrainCard(String authId, String cardId, String gameId);
    String swapTrainCard(String authId, String gameId, String oldCardId);
    String claimRoute(String authId, String gameId, String routeId, SharedColor color);
}
