package com.example.model;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLeaveException;
import com.example.Exceptions.FailedLoginException;
import com.example.communication.IServerAccessor;
import com.example.gameModel.GameFacade;
import com.example.gameModel.GameModel;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ninjup on 9/26/17.
 */

public class ServerFacade implements IServerAccessor
{

    private  ServerModel model = ServerModel.instance();

    public String login(String username, String password) throws FailedLoginException {
        try {
            return model.verifyPassword(username, password);
        } catch (FailedLoginException e) {
            throw e;
        }
    }

    //Do we need this? Nothing in the server actually happens if they log out
    public boolean logoff(String authToken) throws FailedLoginException {

        return false;
    }

    public String register(String username, String password) throws FailedLoginException {
        String authToken = model.addUser(username, password);
        if (authToken != null) {
            return authToken;
        } else {
            throw new FailedLoginException("Username taken");
        }
    }

    public List<BaseGameSummary> getGames(String authToken) throws FailedAuthException {

        if (model.findPlayerFromToken(authToken) != null) {
            return model.getGamesList();
        } else {
            throw new FailedAuthException("Authentication failed");
        }
    }

    public boolean joinGame(String gameId, SharedColor color, String authToken) throws FailedAuthException, FailedJoinException {
        User player = model.findPlayerFromToken(authToken);
        if (player == null) {
            throw new FailedAuthException("Authentication failed");
        }

        try {
            model.addPlayerToGame(gameId, player, color);
            return true;
        } catch (FailedJoinException e) {
            throw e;
        }
    }

    public boolean leaveGame(String gameId, String authToken) throws FailedAuthException, FailedLeaveException {
        User player = model.findPlayerFromToken(authToken);
        if (player == null) {
            throw new FailedAuthException("Authentication failed");
        }

        try {
            model.removePlayerFromGame(gameId, player);
            return true;
        } catch(FailedLeaveException e) {
            throw e;
        }
    }

    public String createGame(String gameName, SharedColor color, String authToken) throws FailedAuthException {
        User owner = model.findPlayerFromToken(authToken);
        if (owner == null) {
            throw new FailedAuthException("Authentication failed");
        }

        return model.addGame(owner, gameName, color);
    }

    public boolean startGame(String gameId, String authToken) throws FailedAuthException {
        if (model.findPlayerFromToken(authToken) != null) {
            boolean success = model.startGame(gameId);
            if (success) {
                String fullGameId = model.findGameById(gameId).getFullGameId();
                GameFacade accessor = new GameFacade();
                accessor.endTurn(authToken, fullGameId);

                BaseGameSummary game = model.findGameById(gameId);
                Set<String> playerNames = game.getPlayers().keySet();
                List<String> playerTokens = new ArrayList<>();
                for (String s : playerNames) {
                    playerTokens.add(model.findPlayerFromName(s).getAuthToken());
                }

                for (String s : playerTokens) {
                    accessor.drawDestinationCard(s, fullGameId);
                    accessor.drawDestinationCard(s, fullGameId);
                    accessor.drawDestinationCard(s, fullGameId);

                    accessor.drawTrainCard(s, fullGameId);
                    accessor.drawTrainCard(s, fullGameId);
                    accessor.drawTrainCard(s, fullGameId);
                    accessor.drawTrainCard(s, fullGameId);
                }


            }
            return success;
        } else {
            throw new FailedAuthException("Authentication failed");
        }
    }
}
