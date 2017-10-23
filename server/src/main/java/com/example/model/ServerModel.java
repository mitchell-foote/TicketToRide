package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.UUID;

import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLeaveException;
import com.example.Exceptions.FailedLoginException;
import com.example.gameModel.GameModel;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.Player;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

/**
 * Created by ninjup on 9/24/17.
 */

public class ServerModel {

    private static ServerModel _instance = new ServerModel();

    private Map<String, BaseGameSummary> games;
    private Set<User> users;
    private Map<String, User> authTable;
    private Map<String, User> usernameTable;
    private Map<String, GameModel> fullGames;

    private ServerModel() {
        games = new HashMap<>();
        users = new HashSet<>();
        authTable = new HashMap<>();
        usernameTable = new HashMap<>();
    }

    public static ServerModel instance() {
        return _instance;
    }

    public String addUser(String name, String password) {
        String authToken = UUID.randomUUID().toString();
        User newUser = new User(name, password, authToken);
        if (users.add(newUser)) {
            authTable.put(authToken, newUser);
            usernameTable.put(name, newUser);
            return authToken;
        }
        return null;
    }

    public String addGame(Player owner, String gameName, SharedColor userColor) {
        String gameId = UUID.randomUUID().toString();
        Map<String, SharedColor> players = new HashMap<>();
        players.put(owner.getName(), userColor);
        BaseGameSummary newGame = new BaseGameSummary(gameId, owner.getName(), gameName, players);
        games.put(gameId, newGame);
        return gameId;
    }

    public boolean deleteUser(User user) {
        boolean successfullyRemoved = users.remove(user);
        if (successfullyRemoved) {
            authTable.remove(user.getAuthToken());
            usernameTable.remove(user.getName());
        }
        return successfullyRemoved;
    }

    public boolean deleteGame(String gameId) {
        return (games.remove(gameId) != null);
    }

    public boolean addPlayerToGame(String gameId, User user, SharedColor color) throws FailedJoinException {
        BaseGameSummary game = games.get(gameId);

        if (game != null) {
            try {
                game.addPlayer(user, color);
                return true;
            } catch (FailedJoinException e) {
                throw e;
            }
        }
        throw new FailedJoinException("No game exists for given gameID");
    }

    public boolean removePlayerFromGame(String gameId, User user) throws FailedLeaveException {
        BaseGameSummary game = games.get(gameId);
        if (game != null) {
            boolean successfullyRemoved = game.removePlayer(user);
            if (!successfullyRemoved) {
                throw new FailedLeaveException("User not in game");
            } else {
                if (game.isEmpty()) {
                    deleteGame(gameId);
                }
                return true;
            }
        }

        throw new FailedLeaveException("No game exists for given gameID");
    }

    public User findPlayerFromToken(String authToken) {
        return authTable.get(authToken);
    }

    public BaseGameSummary findGameById(String gameId) {
        return games.get(gameId);
    }

    private void updateAuthToken(User user, String newAuthToken) {
        authTable.remove(user.getAuthToken());
        user.setAuthToken(newAuthToken);
        authTable.put(newAuthToken, user);
    }

    public String verifyPassword(String name, String password) throws FailedLoginException {

        User user = usernameTable.get(name);
        boolean correctPassword = false;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                correctPassword = true;
            }

            if (correctPassword) {
                String newAuthToken = UUID.randomUUID().toString();
                updateAuthToken(user, newAuthToken);
                return newAuthToken;
            } else {
                throw new FailedLoginException("Incorrect password");
            }
        } else {
            throw new FailedLoginException("User not found");
        }
    }

    public List<BaseGameSummary> getGamesList() {
        return new ArrayList<BaseGameSummary>(games.values());
    }

    public boolean startGame(String gameId) {
        BaseGameSummary game = findGameById(gameId);
        if (game != null) {
            game.startGame();
            fullGames.put(gameId, new GameModel(game.getFullGameId(), game.getPlayers()));
            return true;
        }
        return false;
    }

    public GameModel findFullGameById(String gameId) {
        return fullGames.get(gameId);
    }
}