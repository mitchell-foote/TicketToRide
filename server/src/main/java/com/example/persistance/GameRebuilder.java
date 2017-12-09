package com.example.persistance;

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
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.GameModel;
import com.example.model.classes.login.BaseGameSummary;

import java.util.List;

/**
 * Created by ninjup on 12/9/17.
 */

public class GameRebuilder {

    private GameRebuilder(){}

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

    }

    private static void addFaceUpTrainCard(GameModel game, AddFaceUpTrainCardClientCommandData command){

    }

    private static void addTrainCard(GameModel game, AddTrainCardClientCommandData command){

    }

    private static void claimRoute(GameModel game, ClaimRouteClientCommandData command){

    }

    private static void endGame(GameModel game, EndGameClientCommandData command){

    }

    private static void lastRound(GameModel game, LastRoundClientCommandData command){

    }

    private static void longestTrainRouteSwitch(GameModel game, LongestTrainRouteSwitchClientCommandData command){

    }

    private static void nextTurn(GameModel game, NextTurnClientCommandData command){

    }

    private static void postMessage(GameModel game, PostMessageClientCommandData command){

    }

    private static void returnDestinationCard(GameModel game, ReturnDestinationCardClientCommandData command){

    }

    private static void setupTrainCards(GameModel game, SetupTrainCardsClientCommandData command){

    }

}
