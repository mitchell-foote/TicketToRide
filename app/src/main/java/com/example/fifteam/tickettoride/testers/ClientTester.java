package com.example.fifteam.tickettoride.testers;

import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientGameCommandFacade;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;
import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;
import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandRunners.ClientCommandRunner;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
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
        GameServerProxy gameServerProxy = new GameServerProxy();
        ClientFacade facade = ClientFacade.getInstance();
        ClientGameCommandFacade otherFacade = ClientGameCommandFacade.getInstance();
        ClientCommandRunner runner = new ClientCommandRunner(otherFacade);

        try
        {
            String auth = proxy.register("batman", "123456");
            System.out.println("WAIT!!");
            String authAgain = proxy.login("batman", "123456");
            List<BaseGameSummary> gameList = proxy.getGames(authAgain);
            String gameId = proxy.createGame("Batmans Game", SharedColor.BLUE, authAgain);
            String secondPlayer = proxy.register("robin", "123456");
            boolean joined = proxy.joinGame(gameId, SharedColor.GREEN, secondPlayer);
            boolean started = proxy.startGame(gameId,secondPlayer);
            gameList = proxy.getGames(authAgain);
            BaseGameSummary gameSummary = gameList.get(0);
            String fullGameId = gameSummary.getFullGameId();
            gameServerProxy.drawDestinationCard(authAgain,fullGameId);
            gameServerProxy.drawDestinationCard(authAgain, fullGameId);
            List<CommandContainer> commands = gameServerProxy.getClientCommands("ALL", authAgain, fullGameId);
            CommandContainer container = commands.get(commands.size() -1);
            AddDestinationCardClientCommandData data = (AddDestinationCardClientCommandData) container.Data;
            gameServerProxy.returnDestinationCard(authAgain,data.CardIds[0],fullGameId);
            gameServerProxy.endTurn(authAgain, fullGameId);
            commands = gameServerProxy.getClientCommands("ALL", authAgain, fullGameId);
            //runner.runCommandsFromContainer(commands);
            boolean leaved = proxy.leaveGame(gameId, authAgain);
            leaved = proxy.leaveGame(gameId, secondPlayer);


        } catch (Exception e)
        {
            e.printStackTrace();
        }


        try {
            proxy.register("batman", "123again"); //already registered
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            proxy.login("batman", "12fish"); //wrong password
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            proxy.login("fakeperson", "whatever"); //non-existent user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String auth = proxy.register("joker", "54321"); //joining game already in
            String gameId = proxy.createGame("Jokers Game", SharedColor.BLUE, auth);
            proxy.joinGame(gameId, SharedColor.GREEN, auth);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try { //using the same color
            String auth = proxy.register("spiderman", "gwenisdead");
            String otherAuth = proxy.register("greenGoblin", "ikilledher");
            String gameId = proxy.createGame("Because Marvel", SharedColor.RED, auth);
            proxy.joinGame(gameId, SharedColor.RED, otherAuth);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
