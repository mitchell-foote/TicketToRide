package com.example.fifteam.tickettoride.model;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.ReturnDestinationCardAsyncTask;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.TrainCard;

import java.util.List;
import java.util.Observer;

/**
 * Created by samks on 10/30/2017.
 */

class ClientGamePresenterFacade {
    private static final ClientGamePresenterFacade ourInstance = new ClientGamePresenterFacade();

    private ClientGameModel model = ClientGameModel.getInstance();
    public static ClientGamePresenterFacade getInstance() {
        return ourInstance;
    }

    private ClientGamePresenterFacade() {
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

    public void discardDestCard(String cardId){
        String authId = model.getAuthToken();
        String gameId = model.getGameID();
        new ReturnDestinationCardAsyncTask().execute(authId,cardId,gameId);
    }


}
