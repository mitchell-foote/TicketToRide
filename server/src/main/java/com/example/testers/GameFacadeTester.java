package com.example.testers;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLoginException;
import com.example.gameCommunication.commands.classes.commandData.client.SetupTrainCardsClientCommandData;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.classes.fullCommands.client.SetupTrainCardsClientCommand;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.GameFacade;
import com.example.gameModel.GameModel;
import com.example.model.ServerFacade;
import com.example.model.ServerModel;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninjup on 10/31/17.
 */

public class GameFacadeTester {

    public static void main(String[] argv){

        ServerFacade facade = new ServerFacade();
        GameFacade gfacade = new GameFacade();
        ServerModel model = ServerModel.instance();

        try
        {
            String authToken = facade.register("batman", "123456");
            String gameId = facade.createGame("batman's game", SharedColor.BLUE, authToken);
            String secondUser = facade.register("robin", "123456");
            boolean joined = facade.joinGame(gameId, SharedColor.GREEN, secondUser);
            List<BaseGameSummary> gameList = facade.getGames(authToken);
            boolean started = facade.startGame(gameId, authToken);
            BaseGameSummary baseGame = gameList.get(0);
            String fullGameId = baseGame.getFullGameId();

            System.out.println("base game id: " + baseGame.getId());
            System.out.println("full game id: " + fullGameId);

            List<CommandContainer> commands = gfacade.getClientCommands("ALL", authToken, fullGameId);

            String initialCard = null;
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(commands.get(i).getType());
                if (commands.get(i).getType().equals(CommandTypesEnum.SetupTrainCards)) {
                    SetupTrainCardsClientCommandData trainCardData = (SetupTrainCardsClientCommandData) commands.get(i).Data;
                    System.out.println("The Initial Face-Up Train cards are: ");
                    for (int j = 0; j < trainCardData.TrainCards.length; j++) {
                        System.out.println(trainCardData.TrainCards[j]);
                        if (j == 2) {
                            initialCard = trainCardData.TrainCards[j];
                        }
                    }
                    System.out.println();
                }
            }

            gfacade.addFaceUpTrainCard(authToken, initialCard, fullGameId);

            GameModel game = model.findFullGameById(fullGameId);
            String[] faceUpPile = game.getFaceUpTrainCards();

            System.out.println("The New Face-Up Train cards are: ");
            for (int i = 0; i < faceUpPile.length; i++) {
                System.out.println(faceUpPile[i]);
            }
            System.out.println();

            for (int i = 0; i < 20; i++) {
                gfacade.drawTrainCard(authToken, fullGameId);
            }

            for (int i = 0; i < 20; i++) {
                gfacade.drawTrainCard(secondUser, fullGameId);
            }
            System.out.println(game.getPlayerInfo(model.findPlayerFromName("batman")));
            System.out.println(game.getPlayerInfo(model.findPlayerFromName("robin")));

            gfacade.claimRoute(authToken, fullGameId, "5", SharedColor.YELLOW); //reefer
            gfacade.claimRoute(authToken, fullGameId, "1", SharedColor.BLUE); //tanker
            gfacade.claimRoute(authToken, fullGameId, "18", SharedColor.ORANGE); //freight

            gfacade.endTurn(authToken, fullGameId);

            gfacade.claimRoute(secondUser, fullGameId, "13", SharedColor.WHITE); //passenger
            gfacade.claimRoute(secondUser, fullGameId, "12", SharedColor.BLACK); //hopper

            gfacade.endTurn(authToken, fullGameId);

            System.out.println(game.getPlayerInfo(model.findPlayerFromName("batman")));
            System.out.println(game.getPlayerInfo(model.findPlayerFromName("robin")));



        } catch (FailedLoginException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (FailedAuthException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (FailedJoinException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } //catch (FailedLeaveException e)
        //{
        // e.printStackTrace();
        //}


    }
}
