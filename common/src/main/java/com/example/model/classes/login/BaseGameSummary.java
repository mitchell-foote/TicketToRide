package com.example.model.classes.login;

import com.example.model.classes.users.Player;
import com.example.model.enums.SharedColor;


import java.util.Map;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class BaseGameSummary
{
    private String id;
    private String ownerUsername;
    private String gameName;
    private Map<Player, SharedColor> players;

    public BaseGameSummary(String id, String ownerUsername, String gameName, Map<Player, SharedColor> players) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.gameName = gameName;
        this.players = players;
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

    public Map<Player, SharedColor> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Player, SharedColor> players) {
        this.players = players;
    }

    public boolean addPlayer(Player player, SharedColor color) {
        if (players.containsKey(player)){
            return false;
        } else {
            players.put(player, color);
            return true;
        }
    }

    public boolean removePlayer(Player player) {
        if (players.containsKey(player)){
            players.remove(player);
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
}
