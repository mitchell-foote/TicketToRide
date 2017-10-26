package com.example.fifteam.tickettoride.model;

import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.classes.TrainLookupTable;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.List;

/**
 * Created by samks on 10/26/2017.
 *
 * used by the commands to access the model
 */

class ClientGameProxy implements IGameAccessor{
    private static final ClientGameProxy ourInstance = new ClientGameProxy();

    static ClientGameProxy getInstance(){
        if(ourInstance.model == null){
            ourInstance.model = ClientGameModel.getInstance();
        }
        return ourInstance;
    }
    private ClientGameModel model;

    private ClientGameProxy() {
    }


    public ICommandContainer drawTrainCard(String username, String cardId){

        //gets the player whose hand will be adjusted and the card in question
        PlayerGameSummary playerToDraw = model.getPlayerById(username);
        TrainCard toAdd = TrainLookupTable.getCardById(cardId);

        String historyEntry;
        if(playerToDraw instanceof UserGameSummary){
            UserGameSummary userToDraw = (UserGameSummary) playerToDraw;
            userToDraw.addCard(toAdd.getColor());
            historyEntry = "You drew a " + toAdd.getColor() + " card";
            model.addHistoryEntry(historyEntry);
        }
        else{
            playerToDraw.incrementHandSize();
            historyEntry = playerToDraw.getName() + " drew a card";
            model.addHistoryEntry(historyEntry);
        }
        return null;
    }
    public List<ICommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId){
        return null;
    }


    public ICommandContainer postMessage(String message, String username, String gameId){
        ChatEntry chatToAdd = new ChatEntry(username,message);

        model.addChatEntry(chatToAdd);
        return null;
    }
    public ICommandContainer drawDestinationCard(String authId, String gameId){


        return null;
    }
    public ICommandContainer returnDestinationCard(String authId, String cardId, String gameId){


        return null;
    }
    public ICommandContainer endTurn(String authId, String gameId){


        return null;
    }
    public ICommandContainer addFaceUpTrainCard(String authId, String cardId, String gameId){

        return null;

    }
}
