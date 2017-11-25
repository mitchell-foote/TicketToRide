package com.example.fifteam.tickettoride.model;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.GamePollerAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.MapSummary;
import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.enums.City;
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

    private List<String> gameHistory;

    private List<DestinationCard> destinationCardsToChoose;
    private List<TrainCard> faceUpTrainCards;
    private List<Route> routeList;
    private int numOpponentsDestCardsUp;

    private String longestRouteOwner;
    private int longestRouteLength;

    private String nextTurn;
    private String lastExecutedHash;
    private boolean isUserTurn;
    private boolean runningAsync;
    private Toaster toaster;
    private boolean gameOver;
    private boolean firstTurn;
    private boolean pickTurnChoice;

    private Route currentlySelectedRoute;
    private Toaster gameMapToaster;
    private boolean turnChoiceDialogCurrentlyDisplayed;
    private boolean waitingForCardUpdate;

    private ClientGameModel(){
        this.nextTurn = null;
        this.playerSummaryMap = new HashMap<>();
        this.setUserSummary(null);
        this.mapSummary = new MapSummary();
        this.chatHistory = new ArrayList<>();
        this.destinationCardsToChoose = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
        this.lastExecutedHash = "ALL";
        this.faceUpTrainCards = new ArrayList<>();
        routeList = setUpRoutes();
        this.isUserTurn = false;
        this.setNumOpponentsDestCards(0);
        this.runningAsync = false;
        this.gameOver = false;
        longestRouteOwner = null;
        longestRouteLength = 0;
        firstTurn = true;
        currentlySelectedRoute = null;
        waitingForCardUpdate = true;
    }

    public boolean isRunningAsync() {
        return runningAsync;
    }

    public void setRunningAsync(boolean runningAsync) {
        this.runningAsync = runningAsync;
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

    public void updatePlayerTrains(String playerId, int trainsToSubtract){
        PlayerGameSummary playerToUpdate = this.playerSummaryMap.get(playerId);
        playerToUpdate.subtractFromTrainsRemaining(trainsToSubtract);
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
        this.toast("Player: " + nextTurn + " is up!");
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

    public void addDestinationCardsToChoose(List<DestinationCard> destinationCards){
        this.destinationCardsToChoose.addAll(destinationCards);
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

    public void clearFaceUpTrainCards(){
        this.faceUpTrainCards = new ArrayList<>();
        setChanged();
        notifyObservers();
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

    public boolean getPickTurnChoice() {
        return pickTurnChoice;
    }

    public void setPickTurnChoice(boolean pickTurnChoice) {
        this.pickTurnChoice = pickTurnChoice;
        setChanged();
        notifyObservers();
    }

    public Route getCurrentlySelectedRoute() {
        return currentlySelectedRoute;
    }

    public void setCurrentlySelectedRoute(Route currentlySelectedRoute) {
        this.currentlySelectedRoute = currentlySelectedRoute;
    }

    public Toaster getGameMapToaster() {
        return gameMapToaster;
    }

    public void setGameMapToaster(Toaster gameMapToaster) {
        this.gameMapToaster = gameMapToaster;
    }

    public boolean isTurnChoiceDialogCurrentlyDisplayed() {
        return turnChoiceDialogCurrentlyDisplayed;
    }

    public void setTurnChoiceDialogCurrentlyDisplayed(boolean displayed) {
        turnChoiceDialogCurrentlyDisplayed = displayed;
    }

    public boolean isWaitingForCardUpdate() {
        return waitingForCardUpdate;
    }

    public void setWaitingForCardUpdate(boolean cardUpdate) {
        waitingForCardUpdate = cardUpdate;
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

    public List<PlayerGameSummary> getAllPlayerGameSummaryList(){
        Collection<PlayerGameSummary> playerCollection = this.playerSummaryMap.values();
        List<PlayerGameSummary> toReturn = new ArrayList<>(playerCollection);
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

    public List<Route> setUpRoutes() {

        List<Route> routes = new ArrayList<>();

        Route route1;
        Route route2;

        //double route
        route1 = new Route("1", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh);
        route2 = new Route("2", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("3", SharedColor.RAINBOW, 2, City.Atlanta, City.Charleston));
        routes.add(new Route("4", SharedColor.BLUE, 5, City.Atlanta, City.Miami));

        //double route
        route1 = new Route("5", SharedColor.YELLOW, 4, City.Atlanta, City.New_Orleans);
        route2 = new Route("6", SharedColor.ORANGE, 4, City.Atlanta, City.New_Orleans);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("7", SharedColor.RAINBOW, 1, City.Atlanta, City.Nashville));

        //double route
        route1 = new Route("8", SharedColor.RAINBOW, 2, City.Boston, City.Montreal);
        route2 = new Route("9", SharedColor.RAINBOW, 2, City.Boston, City.Montreal);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("10", SharedColor.YELLOW, 2, City.Boston, City.New_York);
        route2 = new Route("11", SharedColor.RED, 2, City.Boston, City.New_York);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("12", SharedColor.RAINBOW, 3, City.Calgary, City.Vancouver));
        routes.add(new Route("13", SharedColor.WHITE, 6, City.Calgary, City.Winnipeg));
        routes.add(new Route("14", SharedColor.RAINBOW, 4, City.Calgary, City.Helena));
        routes.add(new Route("15", SharedColor.RAINBOW, 4, City.Calgary, City.Seattle));
        routes.add(new Route("16", SharedColor.RAINBOW, 2, City.Charleston, City.Raleigh));
        routes.add(new Route("17", SharedColor.PURPLE, 4, City.Charleston, City.Miami));

        //double route
        route1 = new Route("18", SharedColor.ORANGE, 3, City.Chicago, City.Pittsburgh);
        route2 = new Route("19", SharedColor.BLACK, 3, City.Chicago, City.Pittsburgh);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("20", SharedColor.WHITE, 4, City.Chicago, City.Toronto));
        routes.add(new Route("21", SharedColor.RED, 3, City.Chicago, City.Duluth));
        routes.add(new Route("22", SharedColor.BLUE, 4, City.Chicago, City.Omaha));

        //double route
        route1 = new Route("23", SharedColor.GREEN, 2, City.Chicago, City.Saint_Louis);
        route2 = new Route("24", SharedColor.WHITE, 2, City.Chicago, City.Saint_Louis);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("25", SharedColor.RAINBOW, 2, City.Dallas, City.Little_Rock));

        //double route
        route1 = new Route("26", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City);
        route2 = new Route("27", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("28", SharedColor.RED, 4, City.Dallas, City.El_Paso));

        //double route
        route1 = new Route("29", SharedColor.RAINBOW, 1, City.Dallas, City.Houston);
        route2 = new Route("30", SharedColor.RAINBOW, 1, City.Dallas, City.Houston);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("31", SharedColor.ORANGE, 4, City.Denver, City.Kansas_City);
        route2 = new Route("32", SharedColor.BLACK, 4, City.Denver, City.Kansas_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("33", SharedColor.PURPLE, 4, City.Denver, City.Omaha));
        routes.add(new Route("34", SharedColor.GREEN, 4, City.Denver, City.Helena));

        //double route
        route1 = new Route("35", SharedColor.RED, 3, City.Denver, City.Salt_Lake_City);
        route2 = new Route("36", SharedColor.YELLOW, 3, City.Denver, City.Salt_Lake_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("37", SharedColor.WHITE, 5, City.Denver, City.Phoenix));
        routes.add(new Route("38", SharedColor.RAINBOW, 2, City.Denver, City.Santa_Fe));
        routes.add(new Route("39", SharedColor.RED, 4, City.Denver, City.Oklahoma_City));

        //double route
        route1 = new Route("40", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha);
        route2 = new Route("41", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("42", SharedColor.PURPLE, 6, City.Duluth, City.Toronto));
        routes.add(new Route("43", SharedColor.RAINBOW, 3, City.Duluth, City.Sault_Ste_Marie));
        routes.add(new Route("44", SharedColor.BLACK, 4, City.Duluth, City.Winnipeg));
        routes.add(new Route("45", SharedColor.ORANGE, 6, City.Duluth, City.Helena));
        routes.add(new Route("46", SharedColor.GREEN, 6, City.El_Paso, City.Houston));
        routes.add(new Route("47", SharedColor.YELLOW, 5, City.El_Paso, City.Oklahoma_City));
        routes.add(new Route("48", SharedColor.RAINBOW, 2, City.El_Paso, City.Santa_Fe));
        routes.add(new Route("49", SharedColor.RAINBOW, 3, City.El_Paso, City.Phoenix));
        routes.add(new Route("50", SharedColor.BLACK, 6, City.El_Paso, City.Los_Angeles));
        routes.add(new Route("51", SharedColor.BLUE, 4, City.Helena, City.Winnipeg));
        routes.add(new Route("52", SharedColor.RED, 5, City.Helena, City.Omaha));
        routes.add(new Route("53", SharedColor.PURPLE, 3, City.Helena, City.Salt_Lake_City));
        routes.add(new Route("54", SharedColor.YELLOW, 6, City.Helena, City.Seattle));
        routes.add(new Route("55", SharedColor.RAINBOW, 2, City.Houston, City.New_Orleans));

        //double route
        route1 = new Route("56", SharedColor.BLUE, 2, City.Kansas_City, City.Saint_Louis);
        route2 = new Route("57", SharedColor.PURPLE, 2, City.Kansas_City, City.Saint_Louis);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("58", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha);
        route2 = new Route("59", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("60", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City);
        route2 = new Route("61", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("62", SharedColor.ORANGE, 3, City.Las_Vegas, City.Salt_Lake_City));
        routes.add(new Route("63", SharedColor.RAINBOW, 2, City.Las_Vegas, City.Los_Angeles));
        routes.add(new Route("64", SharedColor.WHITE, 3, City.Little_Rock, City.Nashville));
        routes.add(new Route("65", SharedColor.RAINBOW, 2, City.Little_Rock, City.Saint_Louis));
        routes.add(new Route("66", SharedColor.RAINBOW, 2, City.Little_Rock, City.Oklahoma_City));
        routes.add(new Route("67", SharedColor.GREEN, 3, City.Little_Rock, City.New_Orleans));

        //double route
        route1 = new Route("68", SharedColor.PURPLE, 3, City.San_Francisco, City.Los_Angeles);
        route2 = new Route("69", SharedColor.YELLOW, 3, City.San_Francisco, City.Los_Angeles);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("70", SharedColor.RAINBOW, 3, City.Phoenix, City.Los_Angeles));
        routes.add(new Route("71", SharedColor.RED, 6, City.Miami, City.New_Orleans));
        routes.add(new Route("72", SharedColor.RAINBOW, 3, City.Montreal, City.New_York));
        routes.add(new Route("73", SharedColor.RAINBOW, 3, City.Montreal, City.Toronto));
        routes.add(new Route("74", SharedColor.BLACK, 5, City.Montreal, City.Sault_Ste_Marie));
        routes.add(new Route("75", SharedColor.BLACK, 3, City.Nashville, City.Raleigh));
        routes.add(new Route("76", SharedColor.YELLOW, 4, City.Nashville, City.Pittsburgh));
        routes.add(new Route("77", SharedColor.RAINBOW, 2, City.Nashville, City.Saint_Louis));

        //double route
        route1 = new Route("78", SharedColor.BLACK, 2, City.New_York, City.Washington);
        route2 = new Route("79", SharedColor.ORANGE, 2, City.New_York, City.Washington);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("80", SharedColor.WHITE, 2, City.New_York, City.Pittsburgh));
        routes.add(new Route("81", SharedColor.GREEN, 2, City.New_York, City.Washington));
        routes.add(new Route("82", SharedColor.BLUE, 3, City.Oklahoma_City, City.Santa_Fe));
        routes.add(new Route("83", SharedColor.RAINBOW, 3, City.Phoenix, City.Santa_Fe));
        routes.add(new Route("84", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Washington));
        routes.add(new Route("85", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Raleigh));
        routes.add(new Route("86", SharedColor.GREEN, 5, City.Pittsburgh, City.Saint_Louis));
        routes.add(new Route("87", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Toronto));

        //double route
        route1 = new Route("88", SharedColor.RAINBOW, 1, City.Portland, City.Seattle);
        route2 = new Route("89", SharedColor.RAINBOW, 1, City.Portland, City.Seattle);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("90", SharedColor.BLUE, 6, City.Portland, City.Salt_Lake_City));

        //double route
        route1 = new Route("91", SharedColor.GREEN, 5, City.Portland, City.San_Francisco);
        route2 = new Route("92", SharedColor.PURPLE, 5, City.Portland, City.San_Francisco);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("93", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington);
        route2 = new Route("94", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        //double route
        route1 = new Route("95", SharedColor.ORANGE, 5, City.Salt_Lake_City, City.San_Francisco);
        route2 = new Route("96", SharedColor.WHITE, 5, City.Salt_Lake_City, City.San_Francisco);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        routes.add(new Route("97", SharedColor.RAINBOW, 6, City.Sault_Ste_Marie, City.Winnipeg));
        routes.add(new Route("98", SharedColor.RAINBOW, 2, City.Sault_Ste_Marie, City.Toronto));

        //double route
        route1 = new Route("99", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver);
        route2 = new Route("100", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routes.add(route1);
        routes.add(route2);

        return routes;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public Route claimRouteLocally(String routeID, SharedColor ownerColor) {
        for (Route r : routeList) {
            if (r.getRouteId().equals(routeID)) {
                r.setClaimed(ownerColor);
                setChanged();
                notifyObservers();
                return r;
            }
        }
        return null;
    }

    public void claimRoute(String username, String routeId, SharedColor ownerColor){
        Route claimed = claimRouteLocally(routeId,ownerColor);
        if(claimed == null){
            this.toast("This should not be happening, route does not exist");
            return;
        }
        PlayerGameSummary claimer = this.getPlayerById(username);
        if(claimer == null){
            this.toast("This should also not be happening username not found");
            return;
        }
        if(claimer instanceof UserGameSummary){
            subtractRouteCards(claimed,ownerColor,(UserGameSummary)claimer);
        }
        else{
            int length = claimed.getLength();
            claimer.decTrainHandSize(length);
        }
        int routePoints = claimed.getPoints();
        claimer.incrementPoints(routePoints);
        setChanged();
        notifyObservers();
    }

    private void subtractRouteCards(Route route, SharedColor color, UserGameSummary user){
        int length = route.getLength();
        Map<SharedColor,Integer> hand = user.getHand();
        int routeColorCount = hand.get(color);
        if(routeColorCount >= length){
            routeColorCount -= length;
            hand.put(color,routeColorCount);
        }
        else{
            hand.put(color,0);
            length -= routeColorCount;
            int rainbowCount = hand.get(SharedColor.RAINBOW);
            rainbowCount -= length;
            hand.put(SharedColor.RAINBOW,rainbowCount);
        }
    }

    public String canClaimRoute(String routeId, SharedColor color){
        UserGameSummary user = this.getUserSummary();
        Route route = null;
        for(Route r : this.routeList){
            if(r.getRouteId().equals(routeId)){
                route = r;
                break;
            }
        }
        if(route == null){
            return "route does not exist";
        }
        if(!route.isClaimable()){
            return "route already claimed";
        }
        if(route.getColor() != color && (route.getColor() != SharedColor.RAINBOW)){
            return "Train card color chosen does not match color of the Route";
        }
        Map<SharedColor,Integer> hand = user.getHand();
        if(hand.get(color) >= route.getLength()){
            return null;
        }
        if(color != SharedColor.RAINBOW) {
         if((hand.get(color) + hand.get(SharedColor.RAINBOW)) >= route.getLength()) {
                return null;
            }
        }
        return "You do not have enough cards to claim route";
    }

    public List<Route> getClaimedRoutes() {
        List<Route> claimed_list = new ArrayList<>();
        for (Route r : routeList) {
            if (!r.isClaimable()) {
                claimed_list.add(r);
            }
        }
        return claimed_list;
    }

    public void initGame(BaseGameSummary baseGameSummary, User user){
        String newGameID = baseGameSummary.getFullGameId();
        this.setGameID(newGameID);

        String newAuthToken = user.getAuthToken();
        this.setAuthToken(newAuthToken);

        Map<String, SharedColor> playerMap = baseGameSummary.getPlayers();

        //retrieve the user data from the player map then remove the user from the map so as to not
        //duplicate them
        String userUserName = user.getName();
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
        new GamePollerAsyncTask().execute();

    }

    public void setToaster(Toaster toaster){
        this.toaster = toaster;
    }

    private void toast(String toToast){
        this.toaster.displayMessage(toToast);
    }

    public void endGame(){
        this.gameOver = true;
        setChanged();
        notifyObservers();
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void setLongestRouteOwner(String newOwner){
        this.longestRouteOwner = newOwner;
        setChanged();
        notifyObservers();
    }

    public String getLongestRouteOwner(){
        return this.longestRouteOwner;
    }

    public void setLongestRouteLength(int newLength){
        this.longestRouteLength = newLength;
        setChanged();
        notifyObservers();
    }

    public int getLongestRouteLength(){
        return this.longestRouteLength;
    }

    public void lastTurn(String username){
        String lastTurnMessage = username + " has initiated the last round of the game! Beware!";
        this.toast(lastTurnMessage);
    }

    public boolean isFirstTurn(){
        return this.firstTurn;
    }

    public void setFirstTurn(boolean firstTurn){
        this.firstTurn = firstTurn;
    }

}
