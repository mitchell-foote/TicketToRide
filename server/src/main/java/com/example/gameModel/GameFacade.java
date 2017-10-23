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

    public ICommandContainer addTrainCard(String cardId, String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        game.addTrainCardToPlayerHand(player, cardId);

        return null;
    }

    public List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId) {

        return null;
    }

    public ICommandContainer postMessage(String message, String authId, String gameId) {

        return null;
    }

    public ICommandContainer addDestinationCard(String cardId, String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        game.addDestinationCardToPlayerHand(player, cardId);

        return null;
    }
}
