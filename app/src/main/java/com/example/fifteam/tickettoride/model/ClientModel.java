package com.example.fifteam.tickettoride.model;

import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * The client-side model for storing information about the state of the game and users
 * Is Observable and notifies its observers whenever any data is set anew.
 *
 * Created by kcwillmore on 9/28/17.
 */
public class ClientModel extends Observable{
    private static ClientModel ourInstance = new ClientModel();

    public static ClientModel getInstance() {
        return ourInstance;
    }

    private ClientModel() {
    }

    private User user;
    private BaseGameSummary currentGame;
    private List<BaseGameSummary> gamesList;
    private Map<String, BaseGameSummary> gamesMap;
    private String gameToJoin;

    public String getGameToJoin() {
        return gameToJoin;
    }

    public void setGameToJoin(String gameToJoin) {
        this.gameToJoin = gameToJoin;
    }

    public BaseGameSummary getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(BaseGameSummary currentGame) {
        this.currentGame = currentGame;
        setChanged();
        notifyObservers();
    }

    public List<BaseGameSummary> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<BaseGameSummary> gamesList) {
        this.gamesList = gamesList;
        gamesMap = new HashMap<>();
        for (BaseGameSummary i : gamesList){
            gamesMap.put(i.getId(),i);
        }
        setChanged();
        notifyObservers();
    }

    public BaseGameSummary getGameByID(String id){
        return gamesMap.get(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        setChanged();
        notifyObservers();
        this.user = user;
    }


}
