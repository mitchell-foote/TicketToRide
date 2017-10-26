package com.example.fifteam.tickettoride.model;

import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.MapSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by samks on 10/24/2017.
 */

public class ClientGameModel extends Observable {

    private static ClientGameModel ourInstance = new ClientGameModel();

    public static ClientGameModel getInstance() {
        return ourInstance;
    }

    private Map<String, PlayerGameSummary> playerSummaryMap;
    private UserGameSummary userSummary;
    private MapSummary mapSummary;
    private String gameID;
    private List<ChatEntry> chatHistory;
    private List<DestinationCard> destinationCardsToChoose;
    private List<String> gameHistory;

    private ClientGameModel(){
        this.playerSummaryMap = new HashMap<>();
        this.userSummary = new UserGameSummary();
        this.mapSummary = new MapSummary();
        this.chatHistory = new ArrayList<>();
        this.destinationCardsToChoose = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
        setChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(Observer toAdd){
        super.addObserver(toAdd);
    }

    public void removeObserver(Observer toRemove){
        super.deleteObserver(toRemove);

    }

    public void addPlayer(PlayerGameSummary toAdd){
        this.playerSummaryMap.put(toAdd.getName(),toAdd);
        setChanged();
        notifyObservers();
    }

    public PlayerGameSummary getPlayerById(String id){
        return this.playerSummaryMap.get(id);
    }

    public void updatePlayerCardCount(String playerId, int newCardCount){
        PlayerGameSummary playerToUpdate= this.playerSummaryMap.get(playerId);
        playerToUpdate.incrementHandSize();
        setChanged();
        notifyObservers();
    }

    public void updatePlayerPoints(String playerId, int newPointCount){
        PlayerGameSummary playerToUpdate = this.playerSummaryMap.get(playerId);
        playerToUpdate.incrementPoints(newPointCount);
        setChanged();
        notifyObservers();
    }

    public void updatePlayerTrains(String playerId, int newTrainCount){
        PlayerGameSummary playerToUpdate = this.playerSummaryMap.get(playerId);
        playerToUpdate.setTrainsRemaining(newTrainCount);
        setChanged();
        notifyObservers();
    }

    public List<ChatEntry> getChatHistory(){
        return chatHistory;
    }

    public void addChatEntry(ChatEntry toAdd){
        this.chatHistory.add(toAdd);
        setChanged();
        notifyObservers();
    }

    public List<String> getGameHistory(){
        return this.gameHistory;
    }

    public void addHistoryEntry(String toAdd){
        this.gameHistory.add(toAdd);
        setChanged();
        notifyObservers();
    }






}
