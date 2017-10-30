package com.example.fifteam.tickettoride.model;

import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.MapSummary;
import com.example.gameModel.classes.TrainCard;
import com.example.model.classes.users.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

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
    private String nextTurn;
    private String lastExecutedHash;
    private List<TrainCard> faceUpTrainCards;
    private boolean isUserTurn;

    private ClientGameModel(){
        this.playerSummaryMap = new HashMap<>();
        this.setUserSummary(new UserGameSummary());
        this.mapSummary = new MapSummary();
        this.chatHistory = new ArrayList<>();
        this.destinationCardsToChoose = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
        this.lastExecutedHash = null;
        this.faceUpTrainCards = new ArrayList<>();
        this.isUserTurn = false;
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
        playerToUpdate.incrementTrainHandSize();
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

    public String getNextTurn(){
        return this.nextTurn;
    }

    public void setNextTurn(String userNameToSet){
        this.nextTurn= userNameToSet;
        setChanged();
        notifyObservers();
    }

    public List<DestinationCard> getDestinationCardsToChoose() {
        return destinationCardsToChoose;
    }

    public void setDestinationCardsToChoose(List<DestinationCard> destinationCardsToChoose) {
        this.destinationCardsToChoose = destinationCardsToChoose;
        setChanged();
        notifyObservers();
    }

    public String getLastExecutedHash() {
        return lastExecutedHash;
    }

    public void setLastExecutedHash(String lastExecutedHash) {
        this.lastExecutedHash = lastExecutedHash;
    }

    public List<TrainCard> getFaceUpTrainCards() {
        return faceUpTrainCards;
    }

    public void addToFaceUpTrainCards(TrainCard toAdd) {
        this.faceUpTrainCards.add(toAdd);
        setChanged();
        notifyObservers();
    }

    public boolean isUserTurn() {
        return isUserTurn;
    }

    public void setUserTurn(boolean userTurn) {
        isUserTurn = userTurn;
    }

    public UserGameSummary getUserSummary() {
        return userSummary;
    }

    public void setUserSummary(UserGameSummary userSummary) {
        this.userSummary = userSummary;
    }

    public List<PlayerGameSummary> getPlayerGameSummaryList(){
        Collection<PlayerGameSummary> playerCollection = this.playerSummaryMap.values();
        List<PlayerGameSummary> toReturn = new ArrayList<>(playerCollection);
        toReturn.remove(userSummary);
        return toReturn;
    }

}
