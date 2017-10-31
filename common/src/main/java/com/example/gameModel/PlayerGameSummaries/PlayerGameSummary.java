package com.example.gameModel.PlayerGameSummaries;

import com.example.model.classes.users.Player;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 10/24/2017.
 */

public class PlayerGameSummary {
    private String name;
    private SharedColor color;
    private int trainHandSize;
    private int points;
    private int trainsRemaining;
    private int numDestinationCards;

    public PlayerGameSummary(){

    }

    public PlayerGameSummary(String name, SharedColor color){
        this.name = name;
        this.color = color;
        this.trainHandSize = 0;
        this.points = 0;
        this.numDestinationCards = 0;
        this.trainsRemaining = 45;
    }

    public PlayerGameSummary(String name, SharedColor color, int trainHandSize, int points,
                             int trainsRemaining, int numDestinationCards) {
        this.name = name;
        this.color = color;
        this.trainHandSize = trainHandSize;
        this.points = points;
        this.trainsRemaining = trainsRemaining;
        this.numDestinationCards = numDestinationCards;
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

    public int getTrainHandSize() {
        return trainHandSize;
    }

    public void incrementTrainHandSize() {
        this.trainHandSize++;
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }

    public int getTrainsRemaining() {
        return trainsRemaining;
    }

    public void setTrainsRemaining(int trainsRemaining) {
        this.trainsRemaining = trainsRemaining;
    }

    public int getNumDestinationCards(){
        return this.numDestinationCards;
    }

    public void incrementNumDestCards(int toInc){
        this.numDestinationCards += toInc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerGameSummary)) return false;

        PlayerGameSummary that = (PlayerGameSummary) o;

        if (getTrainHandSize() != that.getTrainHandSize()) return false;
        if (getPoints() != that.getPoints()) return false;
        if (getTrainsRemaining() != that.getTrainsRemaining()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return getColor() == that.getColor();
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + getTrainHandSize();
        result = 31 * result + getNumDestinationCards();
        result = 31 * result + getPoints();
        result = 31 * result + getTrainsRemaining();
        return result;
    }
}
