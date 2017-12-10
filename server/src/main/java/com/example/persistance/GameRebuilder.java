package com.example.persistance;

import com.example.gameCommunication.commands.classes.commandBuilders.ClientCommandDataBuilder;
import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddFaceUpTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ClaimRouteClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.EndGameClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.LastRoundClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.LongestTrainRouteSwitchClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.NextTurnClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ReturnDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.SetupTrainCardsClientCommandData;
import com.example.gameCommunication.commands.classes.fullCommands.client.AddDestinationCardClientCommand;
import com.example.gameCommunication.commands.classes.fullCommands.client.LastRoundClientCommand;
import com.example.gameCommunication.commands.classes.fullCommands.client.PostMessageClientCommand;
import com.example.gameCommunication.commands.classes.fullCommands.client.SetupTrainCardsClientCommand;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.GameFacade;
import com.example.gameModel.GameModel;
import com.example.model.ServerModel;
import com.example.model.classes.login.BaseGameSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ninjup on 12/9/17.
 */

public class GameRebuilder {

    private GameRebuilder(){}

    private static GameFacade gameFacade = new GameFacade();
    private static ClientCommandDataBuilder commandBuilder = new ClientCommandDataBuilder();
    private static ServerModel serverModel = ServerModel.instance();

    public static GameModel reconstructGame(BaseGameSummary baseGame, List<IClientCommandData> commands) {

        GameModel rebuiltGame = new GameModel(baseGame.getFullGameId(), baseGame.getPlayers());


        for (int i = 0; i < commands.size(); i++) {
            IClientCommandData command = commands.get(i);

            if (command instanceof AddDestinationCardClientCommandData) {
                AddDestinationCardClientCommandData nextCommand = (AddDestinationCardClientCommandData) command;
                addDestinationCard(rebuiltGame, nextCommand);

            } else if (command instanceof AddFaceUpTrainCardClientCommandData) {
                AddFaceUpTrainCardClientCommandData nextCommand = (AddFaceUpTrainCardClientCommandData) command;
                addFaceUpTrainCard(rebuiltGame, nextCommand);

            } else if (command instanceof AddTrainCardClientCommandData) {
                AddTrainCardClientCommandData nextCommand = (AddTrainCardClientCommandData) command;
                addTrainCard(rebuiltGame, nextCommand);

            } else if (command instanceof ClaimRouteClientCommandData) {
                ClaimRouteClientCommandData nextCommand = (ClaimRouteClientCommandData) command;
                claimRoute(rebuiltGame, nextCommand);

            } else if (command instanceof EndGameClientCommandData) {
                EndGameClientCommandData nextCommand = (EndGameClientCommandData) command;
                endGame(rebuiltGame, nextCommand);

            } else if (command instanceof LastRoundClientCommandData) {
                LastRoundClientCommandData nextCommand = (LastRoundClientCommandData) command;
                lastRound(rebuiltGame, nextCommand);

            } else if (command instanceof LongestTrainRouteSwitchClientCommandData) {
                LongestTrainRouteSwitchClientCommandData nextCommand = (LongestTrainRouteSwitchClientCommandData) command;
                longestTrainRouteSwitch(rebuiltGame, nextCommand);

            } else if (command instanceof NextTurnClientCommandData) {
                NextTurnClientCommandData nextCommand = (NextTurnClientCommandData) command;
                nextTurn(rebuiltGame, nextCommand);

            } else if (command instanceof PostMessageClientCommandData) {
                PostMessageClientCommandData nextCommand = (PostMessageClientCommandData) command;
                postMessage(rebuiltGame, nextCommand);

            } else if (command instanceof ReturnDestinationCardClientCommandData) {
                ReturnDestinationCardClientCommandData nextCommand = (ReturnDestinationCardClientCommandData) command;
                returnDestinationCard(rebuiltGame, nextCommand);

            } else if (command instanceof SetupTrainCardsClientCommandData) {
                SetupTrainCardsClientCommandData nextCommand = (SetupTrainCardsClientCommandData) command;
                setupTrainCards(rebuiltGame, nextCommand);
            }
        }

        return rebuiltGame;
    }

    private static void addDestinationCard(GameModel game, AddDestinationCardClientCommandData command){
        for (String s : command.CardIds) {
            game.addSpecificDestinationCard(serverModel.findPlayerFromName(command.Username), s);
        }
        game.addCommand(commandBuilder.drawDestinationCard(command.Username, new ArrayList<>(Arrays.asList(command.CardIds))), CommandTypesEnum.AddDestination);
    }

    private static void addFaceUpTrainCard(GameModel game, AddFaceUpTrainCardClientCommandData command){
        game.replayAddFaceUpCard(serverModel.findPlayerFromName(command.Username) ,command.CardId, command.NewCardId);
        game.addCommand(commandBuilder.addFaceUpTrainCard(command.Username, command.CardId, command.NewCardId), CommandTypesEnum.AddFaceUpTrain);
    }

    private static void addTrainCard(GameModel game, AddTrainCardClientCommandData command){
        game.addSpecificTrainCard(serverModel.findPlayerFromName(command.Username), command.CardId);
        game.addCommand(commandBuilder.drawTrainCard(command.Username, command.CardId), CommandTypesEnum.AddTrainCard);
    }

    private static void claimRoute(GameModel game, ClaimRouteClientCommandData command){
        gameFacade.claimRoute(serverModel.findPlayerFromName(command.Username).getAuthToken(), game.getGameId(), command.RouteId, command.Color);
    }

    private static void endGame(GameModel game, EndGameClientCommandData command){
        //do nothing, should be recreated automatically when claimRoute is done
    }

    private static void lastRound(GameModel game, LastRoundClientCommandData command){
        //do nothing, should be recreated automatically when claimRoute is done
    }

    private static void longestTrainRouteSwitch(GameModel game, LongestTrainRouteSwitchClientCommandData command){
        //do nothing, should be recreated automatically when claimRoute is done
    }

    private static void nextTurn(GameModel game, NextTurnClientCommandData command){
        gameFacade.endTurn(null, game.getGameId());
    }

    private static void postMessage(GameModel game, PostMessageClientCommandData command){
        game.addCommand(commandBuilder.postMessage(command.Message, command.Username), CommandTypesEnum.PostMessage);
    }

    private static void returnDestinationCard(GameModel game, ReturnDestinationCardClientCommandData command){
        gameFacade.returnDestinationCard(serverModel.findPlayerFromName(command.Username).getAuthToken(), command.CardId, game.getGameId());
    }

    private static void setupTrainCards(GameModel game, SetupTrainCardsClientCommandData command){
        Set<String> newFaceUpCards = new HashSet<>(Arrays.asList(command.TrainCards));
        game.replaceFiveFaceUpCards(newFaceUpCards);
        game.addCommand(commandBuilder.setupTrainCards(command.TrainCards), CommandTypesEnum.SetupTrainCards);
    }

}
