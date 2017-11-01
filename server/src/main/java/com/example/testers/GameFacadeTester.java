package com.example.testers;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLoginException;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameModel.GameFacade;
import com.example.model.ServerFacade;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.io.IOException;
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
            System.out.println("number of commands: " + commands.size());

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
