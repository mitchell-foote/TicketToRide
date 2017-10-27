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

    public String drawTrainCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawTrainCard(player);

        return cardId;
    }

    public List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId) {

        return null;
    }

    public ICommandContainer postMessage(String message, String authId, String gameId) {

        return null;
    }

    public String drawDestinationCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawDestinationCard(player);

        return cardId;
    }

    public String addFaceUpTrainCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String sameCardId = game.selectFaceUpTrainCard(player, cardId);

        return sameCardId;
    }

    public boolean returnDestinationCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        return game.returnDestinationCard(cardId);
    }

    public String endTurn(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        String nextPlayerName = game.incrementTurn();

        return nextPlayerName;
    }

}
