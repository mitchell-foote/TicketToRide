package com.example.testers;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLeaveException;
import com.example.Exceptions.FailedLoginException;
import com.example.model.ServerFacade;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mitchell Foote on 10/4/2017.
 */

public class TestRunner
{
    public static void main(String[] argv){
        ServerFacade facade = new ServerFacade();
        try
        {
            String authToken = facade.register("batman", "123456");
            String secondAuthToken = facade.login("batman", "123456");
            List<BaseGameSummary> gameList = facade.getGames(secondAuthToken);
            String gameId = facade.createGame("batman's game", SharedColor.BLUE, secondAuthToken);
            String secondUser = facade.register("robin", "123456");
            boolean joined = facade.joinGame(gameId, SharedColor.GREEN, secondUser);
            gameList = facade.getGames(secondAuthToken);
            boolean gonzo = facade.leaveGame(gameId, secondAuthToken);
            boolean allGonzo = facade.leaveGame(gameId, secondUser);
            gameList = facade.getGames(secondUser);
            System.in.read();

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
        } catch (FailedLeaveException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
