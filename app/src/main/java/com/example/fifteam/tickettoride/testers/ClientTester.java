package com.example.fifteam.tickettoride.testers;

import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/4/2017.
 */

public class ClientTester
{
    public static void main(String[] args){
        ServerProxy proxy = new ServerProxy();
        try
        {
            String auth = proxy.register("batman", "123456");
            System.out.println("WAIT!!");
            String authAgain = proxy.login("batman", "123456");
            List<BaseGameSummary> gameList = proxy.getGames(authAgain);
            String gameId = proxy.createGame("Batman's Game", SharedColor.BLUE, authAgain);
            String secondPlayer = proxy.register("robin", "123456");
            boolean joined = proxy.joinGame(gameId, SharedColor.GREEN, secondPlayer);
            boolean leaved = proxy.leaveGame(gameId, authAgain);
            leaved = proxy.leaveGame(gameId, secondPlayer);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
