package com.example.gameModel;

import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameModel.interfaces.IGameAccessor;
import com.example.model.ServerModel;
import com.example.model.classes.users.Player;

import java.util.List;

/**
 * Created by ninjup on 10/22/17.
 */

public class GameFacade implements IGameAccessor {

    private ServerModel model = ServerModel.instance();

    public ICommandContainer drawTrainCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawTrainCard(player);

        return null;
    }

    public List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId) {

        return null;
    }

    public ICommandContainer postMessage(String message, String authId, String gameId) {

        return null;
    }

    public ICommandContainer drawDestinationCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawDestinationCard(player);

        return null;
    }

    public ICommandContainer addFaceUpTrainCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        game.selectFaceUpTrainCard(player, cardId);

        return null;
    }

    public ICommandContainer returnDestinationCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        game.returnDestinationCard(cardId);

        return null;
    }

    public ICommandContainer endTurn(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        String nextPlayerName = game.incrementTurn();

        return null;
    }

}
