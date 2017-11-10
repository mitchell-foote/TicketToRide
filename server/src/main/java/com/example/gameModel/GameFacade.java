package com.example.gameModel;

import com.example.gameCommunication.commands.classes.commandBuilders.ClientCommandDataBuilder;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameModel.interfaces.IGameAccessor;
import com.example.model.ServerModel;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        System.out.println("GameFacade is getting client commands");
        GameModel game = model.findFullGameById(gameId);

        return game.getCommandsFromHash(lastCommandHash);
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
        String secondCard = game.drawDestinationCard(player);
        String thirdCard = game.drawDestinationCard(player);

        List<String> cards = new ArrayList<>();
        cards.add(cardId);
        cards.add(secondCard);
        cards.add(thirdCard);

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

    @Override
    public String swapTrainCard(String authId, String gameId, String oldCardId)
    {
        //TODO Britton: this function
        return null;
    }

    @Override
    public String claimRoute(String authId, String gameId, String routeId)
    {
        //TODO Britton: this function
        return null;
    }

    public void initializeGame(String authId, String baseGameId, String fullGameId) {

        BaseGameSummary gameSummary = model.findGameById(baseGameId);
        Set<String> playerNames = gameSummary.getPlayers().keySet();
        List<String> playerTokens = new ArrayList<>();
        for (String s : playerNames) {
            playerTokens.add(model.findPlayerFromName(s).getAuthToken());
        }

        for (String s : playerTokens) {
            drawDestinationCard(s, fullGameId);

            drawTrainCard(s, fullGameId);
            drawTrainCard(s, fullGameId);
            drawTrainCard(s, fullGameId);
            drawTrainCard(s, fullGameId);
        }

        GameModel game = model.findFullGameById(fullGameId);
        String[] faceUpPile = game.getFaceUpTrainCards();
        game.addCommand(commandBuilder.setupTrainCards(faceUpPile), CommandTypesEnum.SetupTrainCards);
        endTurn(authId, fullGameId);
    }

    public boolean returnDestinationCard(String authId, String cardId, String gameId) {
        GameModel game = model.findFullGameById(gameId);
        Player player = model.findPlayerFromToken(authId);
        boolean success = game.returnDestinationCard(cardId);
        game.removeDestinationCardToPlayerHand(player, cardId);

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
