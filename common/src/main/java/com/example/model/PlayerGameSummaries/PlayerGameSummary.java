package com.example.model.PlayerGameSummaries;

import com.example.model.enums.SharedColor;

/**
 * Created by samks on 10/24/2017.
 */

public class PlayerGameSummary {
    private String name;
    private SharedColor color;
    private int handSize;
    private int points;
    private int trainsRemaining;

    public PlayerGameSummary(){

    }

    public PlayerGameSummary(String name, SharedColor color, int handSize, int points, int trainsRemaining) {
        this.name = name;
        this.color = color;
        this.handSize = handSize;
        this.points = points;
        this.trainsRemaining = trainsRemaining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SharedColor getColor() {
        return color;
    }

    public void setColor(SharedColor color) {
        this.color = color;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTrainsRemaining() {
        return trainsRemaining;
    }

    public void setTrainsRemaining(int trainsRemaining) {
        this.trainsRemaining = trainsRemaining;
    }
}
