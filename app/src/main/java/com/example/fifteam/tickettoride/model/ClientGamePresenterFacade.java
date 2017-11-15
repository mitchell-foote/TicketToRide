package com.example.fifteam.tickettoride.model;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ChatAsyncTask;
import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.EndTurnAsyncTask;
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.facadeEnums.TurnType;
import com.example.fifteam.tickettoride.model.facadeStates.ClaimRouteState;
import com.example.fifteam.tickettoride.model.facadeStates.DrawDestCardState;
import com.example.fifteam.tickettoride.model.facadeStates.DrawTrainCardState;
import com.example.fifteam.tickettoride.model.facadeStates.FacadeState;
import com.example.fifteam.tickettoride.model.facadeStates.NotYourTurnState;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.TrainCard;
import com.example.model.enums.SharedColor;

import java.util.List;
import java.util.Map;
import java.util.Observer;

/** not default
 * Created by samks on 10/30/2017.
 */

public class ClientGamePresenterFacade {
    private static final ClientGamePresenterFacade ourInstance = new ClientGamePresenterFacade();

    private ClientGameModel model = ClientGameModel.getInstance();

    private FacadeState state;

    public static ClientGamePresenterFacade getInstance() {
        return ourInstance;
    }

    private ClientGamePresenterFacade() {
        this.state = new NotYourTurnState();
    }

    public void addObserver(Observer toAdd){
        model.addObserver(toAdd);
    }

    public void removeObserver(Observer toRemove){
        model.removeObserver(toRemove);
    }

    public UserGameSummary getUserGameSummary(){
        return model.getUserSummary();
    }

    public List<String> getGameHistory(){
        return model.getGameHistory();
    }

    public List<ChatEntry> getChatHistory(){
        return model.getChatHistory();
    }

    public List<PlayerGameSummary> getOtherPlayerGameList(){
        return model.getPlayerGameSummaryList();
    }

    public List<TrainCard> getFaceUpTrainCards(){
        return model.getFaceUpTrainCards();
    }

    public List<DestinationCard> getFaceUpDestinationCards(){
        return model.getDestinationCardsToChoose();
    }

    public String getNextTurn(){
        return model.getNextTurn();
    }

    public boolean isUserTurn(){
        return model.isUserTurn();
    }

    public void postChat(String message){
        new ChatAsyncTask().execute(message);
    }

    public void discardDestCard(String cardId, Toaster toaster){
        state.returnDestCard(cardId,toaster);
    }

    public Map<SharedColor, Integer> getUserHand() {
        return model.getUserSummary().getHand();
    }

    public int getUserTrainCount() {
        return model.getUserSummary().getTrainsRemaining();
    }

    public List<Route> getRouteList() {
        return model.getRouteList();
    }

    public void claimRouteLocally(String routeID, SharedColor ownerColor) {
        model.claimRouteLocally(routeID, ownerColor);
    }

    public List<Route> getClaimedRoutes() {
        return model.getClaimedRoutes();
    }

    public int getUserPoints() {
        return model.getUserSummary().getPoints();
    }

    public void updatePlayerPoints(int newPointCount) {
        String playerID = model.getUserSummary().getName();
        model.updatePlayerPoints(playerID, newPointCount);
    }

    public void updatePlayerTrains(int trainsToSubtract) {
        String playerID = model.getUserSummary().getName();
        model.updatePlayerTrains(playerID, trainsToSubtract);
    }

    public List<DestinationCard> getUserDestinations() {
        return model.getUserSummary().getDestinations();
    }

    public void addDestinations(List<DestinationCard> destinations) {
        model.getUserSummary().addDestination(destinations);
    }

    public void demoTest() {

        claimRouteLocally("5", SharedColor.RED);
        claimRouteLocally("10", SharedColor.BLUE);
        claimRouteLocally("15", SharedColor.PURPLE);
        claimRouteLocally("20", SharedColor.RED);
        claimRouteLocally("25", SharedColor.GREEN);
        claimRouteLocally("30", SharedColor.BLUE);
        claimRouteLocally("35", SharedColor.PURPLE);
    }

    public void passToaster(Toaster toaster){
        model.setToaster(toaster);
    }

    public void endTurn(){
        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        new EndTurnAsyncTask().execute(authId,gameId);
        this.state = new NotYourTurnState();
    }

    public boolean isEndGame(){
        return  model.isGameOver();
    }

    public String getLongestRouteOwner(){
        return model.getLongestRouteOwner();
    }

    public int getLongestRouteLength(){
        return model.getLongestRouteLength();
    }

    public void claimRoute(String routeid, SharedColor color, Toaster toaster){
        state.claimRoute(routeid,color,toaster);
    }

    public void drawTrainCard(String cardId, Toaster toaster){
        state.drawTrainCard(cardId,toaster);
    }

    public boolean canDrawTrainCard(){
        return state.canDrawTrainCard();
    }

    public boolean canDrawDestCard(){
        return state.canDrawDestCard();
    }

    public boolean canClaimRoute(){
        return state.canClaimRoute();
    }

    public void setTurnChoice(TurnType turnChoice){
        switch (turnChoice){
            case ClaimRoute:
                this.state = new ClaimRouteState(this);
                break;
            case NotYourTurn:
                this.state = new NotYourTurnState();
                break;
            case DrawDestCard:
                this.state = new DrawDestCardState(this);
                break;
            case DrawTrainCard:
                this.state = new DrawTrainCardState(this);
                break;
            default:
                break;
        }
    }

    public boolean isFirstTurn(){
        return model.isFirstTurn();
    }
}
