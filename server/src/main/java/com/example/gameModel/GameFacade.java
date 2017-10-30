package com.example.gameModel;

import com.example.gameCommunication.commands.classes.commandBuilders.ClientCommandDataBuilder;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameModel.interfaces.IGameAccessor;
import com.example.model.ServerModel;
import com.example.model.classes.users.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninjup on 10/22/17.
 */

public class GameFacade implements IGameAccessor {

    private ServerModel model = ServerModel.instance();
    private ClientCommandDataBuilder commandBuilder = new ClientCommandDataBuilder();

    public String drawTrainCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawTrainCard(player);

        game.addCommand(commandBuilder.drawTrainCard(player.getName(), cardId), CommandTypesEnum.AddTrainCard);

        return cardId;
    }

    public List<CommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);

        return game.getCommandsFromHash(lastCommandHash);
    }

    //I don't know if we need this, but I made it just in case
    public List<IClientCommandData> getClientCommandsData(String lastCommandHash, String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);

        return game.getCommandDataFromHash(lastCommandHash);
    }

    public void postMessage(String message, String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);

        game.addCommand(commandBuilder.postMessage(message, player.getName()), CommandTypesEnum.PostMessage);

        return;
    }

    public String drawDestinationCard(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String cardId = game.drawDestinationCard(player);
        List<String> cards = new ArrayList<>();
        cards.add(cardId);

        game.addCommand(commandBuilder.drawDestinationCard(player.getName(), cards), CommandTypesEnum.AddDestination);

        return cardId;
    }

    public String addFaceUpTrainCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        String replacementCard = game.selectFaceUpTrainCard(player, cardId);

        game.addCommand(commandBuilder.addFaceUpTrainCard(player.getName(), cardId, replacementCard), CommandTypesEnum.AddFaceUpTrain);

        return replacementCard;
    }

    public boolean returnDestinationCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        boolean success = game.returnDestinationCard(cardId);

        game.addCommand(commandBuilder.returnDestinationCard(player.getName(), cardId), CommandTypesEnum.ReturnDestinationCard);

        return success;
    }

    public String endTurn(String authId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        String nextPlayerName = null;

        if (game != null) {
            nextPlayerName = game.incrementTurn();
            game.addCommand(commandBuilder.nextTurn(nextPlayerName), CommandTypesEnum.NextOrEndTurn);
        }
        System.out.println("New turn: " + nextPlayerName + " will go next");
        return nextPlayerName;
    }

}
