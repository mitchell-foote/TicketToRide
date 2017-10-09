package com.example.fifteam.tickettoride.model;

import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.Player;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The client-side model for storing information about the state of the game and users
 * Is Observable and notifies its observers whenever any data is set anew.
 *
 * Created by kcwillmore on 9/28/17.
 */
public class ClientModel extends Observable {
    private static ClientModel ourInstance = new ClientModel();

    public static ClientModel getInstance() {
        return ourInstance;
    }

    private User user;
    private BaseGameSummary currentGame;
    private List<BaseGameSummary> gamesList;
    private Map<String, BaseGameSummary> gamesMap;
    private String gameToJoin;
    private boolean pollerContinue;
    private boolean loggedOut;

    @Override
    public String toString() {
        return "ClientModel{" +
                "user=" + user +
                ", currentGame=" + currentGame +
                ", gamesList=" + gamesList +
                ", gameToJoin='" + gameToJoin + '\'' +
                '}';
    }

    private ClientModel() {

        setGamesList(new ArrayList<BaseGameSummary>());

        this.setPollerContinue(false);
        this.loggedOut  = false;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }



    public boolean isPollerContinue() {
        return pollerContinue;
    }

    public void setPollerContinue(boolean pollerContinue) {
        this.pollerContinue = pollerContinue;
    }

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
        //this.gamesList = gamesList;
        if(this.gamesList == null){
            this.gamesList = gamesList;
        }
        else{
            this.gamesList.clear();
            this.gamesList.addAll(gamesList);
        }
        gamesMap = new HashMap<>();
        if(gamesList != null) {
            for (BaseGameSummary i : gamesList) {
                gamesMap.put(i.getId(), i);
            }
        }
        if(currentGame != null){
            BaseGameSummary newGameSummary = gamesMap.get(currentGame.getId());
            if(newGameSummary != null) {
                currentGame = newGameSummary;
            }
        }
        if(this.gameToJoin != null){
            BaseGameSummary tempGameToJoin = this.gamesMap.get(this.gameToJoin);
            if(tempGameToJoin != null){
                this.currentGame = tempGameToJoin;
                this.gameToJoin = null;
            }
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
        this.user = user;
        setChanged();
        notifyObservers();
    }

}
