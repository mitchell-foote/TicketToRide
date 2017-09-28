package com.example.model;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLeaveException;
import com.example.Exceptions.FailedLoginException;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by ninjup on 9/26/17.
 */

public class ServerFacade {

    private static ServerModel model = ServerModel.instance();

    public static String login(String username, String password) throws FailedLoginException {
        try {
            return model.verifyPassword(username, password);
        } catch (FailedLoginException e) {
            throw e;
        }
    }

    //Do we need this? Nothing in the server actually happens if they log out
    public static boolean logoff(String authToken) throws FailedLoginException {

        return false;
    }

    public static String register(String username, String password) throws FailedLoginException {
        String authToken = model.addUser(username, password);
        if (authToken != null) {
            return authToken;
        } else {
            throw new FailedLoginException("Username taken");
        }
    }

    public static List<BaseGameSummary> getGames(String authToken) throws FailedAuthException {

        if (model.findPlayerFromToken(authToken) != null) {
            return model.getGamesList();
        } else {
            throw new FailedAuthException();
        }
    }

    public static boolean joinGame(String gameId, SharedColor color, String authToken) throws FailedAuthException, FailedJoinException {
        User player = model.findPlayerFromToken(authToken);
        if (player == null) {
            throw new FailedAuthException();
        }

        try {
            model.addPlayerToGame(gameId, player, color);
            return true;
        } catch (FailedJoinException e) {
            throw e;
        }
    }

    public static boolean leaveGame(String gameId, String authToken) throws FailedAuthException, FailedLeaveException {
        User player = model.findPlayerFromToken(authToken);
        if (player == null) {
            throw new FailedAuthException();
        }

        try {
            model.removePlayerFromGame(gameId, player);
            return true;
        } catch(FailedLeaveException e) {
            throw e;
        }
    }

    public static String createGame(String gameName, SharedColor color, String authToken) throws FailedAuthException {
        User owner = model.findPlayerFromToken(authToken);
        if (owner == null) {
            throw new FailedAuthException();
        }

        return model.addGame(owner, gameName, color);
    }
}
