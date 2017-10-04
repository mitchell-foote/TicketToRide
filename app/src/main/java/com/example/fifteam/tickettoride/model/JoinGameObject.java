package com.example.fifteam.tickettoride.model;

import com.example.model.enums.SharedColor;

/**
 * Created by sam on 10/4/17.
 */

public class JoinGameObject {
    String gameId;
    SharedColor color;

    public JoinGameObject(String gameId, SharedColor color) {
        this.gameId = gameId;
        this.color = color;
    }

    public String getGameId() {

        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public SharedColor getColor() {
        return color;
    }

    public void setColor(SharedColor color) {
        this.color = color;
    }
}
