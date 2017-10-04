package com.example.fifteam.tickettoride.model;

import com.example.model.enums.SharedColor;

/**
 * Created by sam on 10/4/17.
 */

public class CreateGameObject {
    private String gameName;
    private SharedColor playerColor;

    public CreateGameObject(String gameName, SharedColor playerColor) {
        this.gameName = gameName;
        this.playerColor = playerColor;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public SharedColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(SharedColor playerColor) {
        this.playerColor = playerColor;
    }
}
