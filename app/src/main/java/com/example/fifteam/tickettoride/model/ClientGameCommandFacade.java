package com.example.fifteam.tickettoride.model;

import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.gameModel.PlayerGameSummaries.UserGameSummary;
import com.example.gameModel.classes.ChatEntry;
import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.DestinationLookupTable;
import com.example.gameModel.classes.PlayerScoreContainer;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.classes.TrainLookupTable;
import com.example.gameModel.interfaces.IClientCommandAccessor;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samks on 10/26/2017.
 *
 * used by the commands to access the model
 */

public class ClientGameCommandFacade implements IClientCommandAccessor{
    private static final ClientGameCommandFacade ourInstance = new ClientGameCommandFacade();

    public static ClientGameCommandFacade getInstance(){
        if(ourInstance.model == null){
            ourInstance.model = ClientGameModel.getInstance();
        }
        return ourInstance;
    }
    private ClientGameModel model;

    private ClientGameCommandFacade() {
    }


    public void drawTrainCard(String username, String cardId){

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
            playerToDraw.incrementTrainHandSize();
            historyEntry = playerToDraw.getName() + " drew a card";
            model.addHistoryEntry(historyEntry);
        }
       return;
    }


    public void postMessage(String message, String username){
        ChatEntry chatToAdd = new ChatEntry(username,message);

        model.addChatEntry(chatToAdd);
        return;
    }

    public void drawDestinationCard(String username, List<String> cardId){
        //gets the player who corresponds to the given id
        PlayerGameSummary playerToDraw = model.getPlayerById(username);

        //if player who drew cards is the user enter if to deal with cards, else return
        if(playerToDraw instanceof UserGameSummary){
            //cast the player to proper type
            UserGameSummary userToDraw = (UserGameSummary) playerToDraw;

            //iterate through list of card ids and add the respective destination cards to list
            ArrayList<DestinationCard> newDestinationCardList = new ArrayList<>();
            for(String s : cardId){
                DestinationCard tempCard = DestinationLookupTable.getCardById(s);
                newDestinationCardList.add(tempCard);
            }
            //set the list in the model as list of destination cards to choose from
            model.addDestinationCardsToChoose(newDestinationCardList);

            //prepare entry for game history
            String historyEntry = "You drew " + cardId.size() + " destination cards!";
            model.addHistoryEntry(historyEntry);
        }
        else{
            for(String s : cardId) {
                playerToDraw.incFaceUpDestCards();
            }
        }
    }
    public void returnDestinationCard(String username, String cardId){

        //gets player who responds to given id
        PlayerGameSummary playerGameSummary = model.getPlayerById(username);

        //if player is the user enter if else return
        if(playerGameSummary instanceof UserGameSummary){
            UserGameSummary currUser = (UserGameSummary) playerGameSummary;

            //get the current list of destination cards and remove the card which is being returned
            //if one exists
            List<DestinationCard> currDestinationCards = model.getDestinationCardsToChoose();
            if(cardId != null) {
                DestinationCard cardToRemove = DestinationLookupTable.getCardById(cardId);
                currDestinationCards.remove(cardToRemove);
            }

            //add the remaining cards to the players current hand of destination cards
            currUser.addDestination(currDestinationCards);

            //sets the current list of destination cards to draw back to empty in the model
            model.setDestinationCardsToChoose(new ArrayList<DestinationCard>());

            //generate the history entries for having chosen these cards
            this.generateDestinationCardHistories(currDestinationCards);

        }
        else{
            playerGameSummary.decFaceUpDestCards();
        }

    }

    private void generateDestinationCardHistories(List<DestinationCard> toGenerate){
        for(DestinationCard d : toGenerate){
            String currEntry = "You drew Destination card; City 1: " + d.getFirstCity() + " City 2: "
                    + d.getSecondCity() + " worth " + d.getValue() + " points.";
            model.addHistoryEntry(currEntry);
        }
    }

    public void setCommandHash(String toSet){
        model.setLastExecutedHash(toSet);
    }

    @Override
    public void setupTrainCards(String[] cards)
    {
        //TODO SAM: this function
    }

    @Override
    public void swapTrainCards(String username, String oldTrainCard, String newTrainCard)
    {
        //TODO SAM: this function
    }

    @Override
    public void claimRoute(String username, String routeId, SharedColor color)
    {
        //TODO SAM: this function
    }

    @Override
    public void endGame(PlayerScoreContainer[] scores)
    {
        //TODO SAM: this function
    }

    @Override
    public void longestTrainRouteSwitch(String username, String longestLength)
    {
        // TODO SAM: this function
    }

    @Override
    public void lastRound(String username)
    {
        //TODO SAM: this function
    }

    public void addFaceUpTrainCard(String username, String cardId, String newCardId){
        //get current face up cards
        List<TrainCard> faceUpTrains = model.getFaceUpTrainCards();

        //get train cards which correspond to the cards in question
        TrainCard newCard = TrainLookupTable.getCardById(newCardId);
        TrainCard oldCard = TrainLookupTable.getCardById(cardId);


        //removes the card which was replaced and adds the newly drawn card
        faceUpTrains.remove(oldCard);
        faceUpTrains.add(newCard);

        //creates and enters the history entry associated with the action taken
        String historyEntry = this.generateFaceUpTrainHist(username,oldCard,newCard);
        model.addHistoryEntry(historyEntry);

    }

    private String generateFaceUpTrainHist(String username, TrainCard oldCard, TrainCard newCard){
        StringBuilder toReturn = new StringBuilder();
        UserGameSummary currUser = model.getUserSummary();
        String currUserName = currUser.getName();
        if(currUserName.equals(username)){
            toReturn.append("You");
        }
        else{
            toReturn.append(username);
        }
        String toAppend = " took a " + oldCard.getColor() + " which was replaced with a " +
                newCard.getColor() + " card.";
        return toReturn.toString();
    }

    public void nextTurn(String nextUser){
        UserGameSummary currUser = model.getUserSummary();
        String currUserName = currUser.getName();

        if(nextUser.equals(currUserName)){
            model.setUserTurn(true);
        }
        else{
            model.setUserTurn(false);
        }
        String currTurn = model.getNextTurn();


        if(currTurn != null) {
            String endTurnEntry;
            if (currUser.getName().equals(currTurn)) {
                if(!model.getDestinationCardsToChoose().isEmpty()){
                    this.returnDestinationCard(currUserName,null);
                }
                endTurnEntry = "You ended your turn.";
            } else {
                PlayerGameSummary currPlayer = model.getPlayerById(currTurn);

                endTurnEntry = currTurn + " ended their turn.";
                if(currPlayer.getFaceUpDestCards() > 0){
                    String destEntry = currTurn + " picked up " + currPlayer.getFaceUpDestCards() +
                            " destination cards.";
                    model.addHistoryEntry(destEntry);
                    model.setNumOpponentsDestCards(0);
                }
            }
            model.addHistoryEntry(endTurnEntry);
        }
        model.setNextTurn(nextUser);


    }
}
