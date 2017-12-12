package com.example.model.classes.login;

import com.example.Exceptions.FailedJoinException;
import com.example.model.classes.users.Player;
import com.example.model.enums.SharedColor;


import java.util.Map;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class BaseGameSummary
{
    private String id;
    private String ownerUsername;
    private String gameName;
    public Map<String, SharedColor> players;
    private Boolean started;
    private String fullGameId;

    public BaseGameSummary(String id, String ownerUsername, String gameName, Map<String, SharedColor> players) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.gameName = gameName;
        this.players = players;
        started = false;
        fullGameId = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return ownerUsername;
    }

    public void setOwner(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setFullGameId(String fullGameId) {
        this.fullGameId = fullGameId;
    }

    public Map<String, SharedColor> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, SharedColor> players) {
        this.players = players;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean isFull() {
        return (players.size() >= 5 );
    }

    public boolean isEmpty() {
        return (players.size() == 0);
    }

    public Boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started){
        this.started = started;
        if (started) {
        }
    }

    public void startGame() {
        started = true;
    }

    public boolean addPlayer(Player player, SharedColor color) throws FailedJoinException {
        if (isFull()){
            throw new FailedJoinException("Game has maximum players");
        } else if (players.containsKey(player.getName())){
            throw new FailedJoinException("User is already in game");
        } else if (players.containsValue(color)){
            throw new FailedJoinException("Color already taken");
        } else {
            players.put(player.getName(), color);
            return true;
        }
    }

    public boolean removePlayer(Player player) {
        if (players.containsKey(player.getName())){
            players.remove(player.getName());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals (Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        BaseGameSummary gameSum = (BaseGameSummary) object;
        return (gameSum.getId().equals(this.getId()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.getId().length(); i++) {
            hash = hash*31 + this.getId().charAt(i);
        }
        return hash;
    }

    public String getFullGameId() {
        return fullGameId;
    }
}