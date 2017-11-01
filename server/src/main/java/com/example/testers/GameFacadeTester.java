package com.example.testers;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLoginException;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.GameFacade;
import com.example.gameModel.GameModel;
import com.example.model.ServerFacade;
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

            gfacade.drawTrainCard(authToken, fullGameId);
            gfacade.drawTrainCard(authToken, fullGameId);
            gfacade.drawTrainCard(authToken, fullGameId);
            gfacade.drawTrainCard(authToken, fullGameId);

            String firstDesCard = gfacade.drawDestinationCard(authToken, fullGameId);
            String secondDesCard = gfacade.drawDestinationCard(authToken, fullGameId);
            String thirdDesCard = gfacade.drawDestinationCard(authToken, fullGameId);

            gfacade.returnDestinationCard(authToken, firstDesCard, fullGameId);
            gfacade.returnDestinationCard(authToken, secondDesCard, fullGameId);
            gfacade.returnDestinationCard(authToken, thirdDesCard, fullGameId);


            commands = gfacade.getClientCommands("ALL", authToken, fullGameId);

            List<String> hashes = new ArrayList<>();

            for (int i = 0; i < commands.size(); i++) {
                hashes.add(((IClientCommandData) commands.get(i).Data).getCommandHash());
            }

            for (int i = 0; i < hashes.size(); i++) {
                System.out.println(hashes.get(i));
            }


            commands = gfacade.getClientCommands(hashes.get(4), authToken, fullGameId);

            List<String> secondHashes = new ArrayList<>();
            for (int i = 0; i < commands.size(); i++) {
                secondHashes.add(((IClientCommandData) commands.get(i).Data).getCommandHash());
            }

            for (int i = 0; i < secondHashes.size(); i++) {
                System.out.println(secondHashes.get(i));
            }


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
