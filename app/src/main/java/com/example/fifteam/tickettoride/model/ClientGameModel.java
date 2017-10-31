package com.example.fifteam.tickettoride.model;

import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.MapSummary;
import com.example.gameModel.classes.TrainCard;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

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


    private String authToken;
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
    private int numOpponentsDestCardsUp;

    private ClientGameModel(){
        this.nextTurn = null;
        this.playerSummaryMap = new HashMap<>();
        this.setUserSummary(null);
        this.mapSummary = new MapSummary();
        this.chatHistory = new ArrayList<>();
        this.destinationCardsToChoose = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
        this.lastExecutedHash = null;
        this.faceUpTrainCards = new ArrayList<>();
        this.isUserTurn = false;
        this.setNumOpponentsDestCards(0);
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

    public void addMultHistoryEntry(List<String> toAdd){
        for(String s : toAdd){
            this.gameHistory.add(s);
        }
        setChanged();
        notifyObservers();
    }

    public String getNextTurn(){
        return this.nextTurn;
    }

    public void setNextTurn(String userNameToSet){
        this.nextTurn= userNameToSet;
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

    public int getNumOpponentsDestCards() {
        return numOpponentsDestCardsUp;
    }

    public void setNumOpponentsDestCards(int numOpponentsDestCards) {
        numOpponentsDestCardsUp = numOpponentsDestCards;
    }

    public void incOpponentDestUp(){
        this.numOpponentsDestCardsUp++;
    }

    public void decOpponentDestUp(){
        this.numOpponentsDestCardsUp--;
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void initGame(BaseGameSummary baseGameSummary, User user){
        String newGameID = baseGameSummary.getFullGameId();
        this.setGameID(newGameID);

        String newAuthToken = user.getAuthToken();
        this.setAuthToken(newAuthToken);

        Map<String, SharedColor> playerMap = baseGameSummary.getPlayers();

        //retrieve the user data from the player map then remove the user from the map so as to not
        //duplicate them
        String userUserName = baseGameSummary.getOwner();
        SharedColor userColor = playerMap.get(userUserName);
        playerMap.remove(userUserName);


        //set the user player and add them to the player list
        UserGameSummary userGameSummary = new UserGameSummary(userUserName,userColor);
        this.setUserSummary(userGameSummary);
        this.addPlayer(userGameSummary);

        //get the set of keys for players in the map
        Set<String> keySet = playerMap.keySet();

        //iterate over the set and create new player objects for each of the entries
        for(String s : keySet){
            SharedColor playerColor = playerMap.get(s);
            PlayerGameSummary playerGameSummary = new PlayerGameSummary(s,playerColor);
            this.addPlayer(playerGameSummary);
        }

        setChanged();
        notifyObservers();

    }
}
