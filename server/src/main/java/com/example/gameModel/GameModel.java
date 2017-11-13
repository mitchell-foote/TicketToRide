package com.example.gameModel;

import com.example.communication.commands.ICommand;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.classes.DestinationDeck;
import com.example.gameModel.classes.PlayerScoreContainer;
import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.TrainCard;
import com.example.gameModel.classes.TrainDeck;
import com.example.model.RouteManager;
import com.example.model.ServerModel;
import com.example.model.classes.users.Player;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ninjup on 10/22/17.
 */

public class GameModel {

    private String gameId;
    private List<Player> orderedPlayers;
    private Map<Player, PlayerInfo> playerInfo;
    private int nextTurn;

    private DestinationDeck desDeck;
    private TrainDeck trainDeck;

    private List<IClientCommandData> commands;
    private List<CommandTypesEnum> correspondingCommTypes;
    private RouteManager routeManager;

    private boolean longestTrainSwitched = false;
    private List<Player> playersWithLongestTrain = null;

    private boolean finalTurnStarted = false;
    private String playerWhoGoesLast = null;

    public GameModel(String gameId, Map<String, SharedColor> players) {
        this.gameId = gameId;
        orderedPlayers = new ArrayList<>();
        playerInfo = new HashMap<>();

        for (Map.Entry<String, SharedColor> entry : players.entrySet()) {
            Player player = ServerModel.instance().findPlayerFromName(entry.getKey());
            orderedPlayers.add(player);
            playerInfo.put(player, new PlayerInfo(entry.getValue()));
        }

        nextTurn = 0;
        desDeck = new DestinationDeck();
        trainDeck = new TrainDeck();
        commands = new ArrayList<>();
        correspondingCommTypes = new ArrayList<>();
        routeManager = new RouteManager(orderedPlayers);
    }

    public PlayerScoreContainer[] calculateScore() {
        PlayerScoreContainer[] scores = new PlayerScoreContainer[orderedPlayers.size()];

        for (int i = 0; i < scores.length; i++) {
            Player nextPlayer = orderedPlayers.get(i);
            PlayerInfo playerHand = playerInfo.get(nextPlayer);
            int totalPoints = playerHand.getScoreFromRoutes();
            totalPoints += routeManager.calculateDestinationCardBonus(nextPlayer, playerHand.getDestinationCardIds());
            totalPoints += routeManager.addLongestRoadBonus(nextPlayer);
            scores[i] = new PlayerScoreContainer(nextPlayer.getName(), Integer.toString(totalPoints));
        }

        return scores;
    }


    public void addCommand(IClientCommandData command, CommandTypesEnum type) {
        commands.add(command);
        correspondingCommTypes.add(type);
    }

    public List<CommandContainer> getCommandsFromHash(String lastHash) {
        List<CommandContainer> recentCommands = new ArrayList<>();
        if (lastHash.equals("ALL")) {
            for (int i = 0; i < commands.size(); i++) {
                recentCommands.add(new CommandContainer(correspondingCommTypes.get(i), commands.get(i)));
            }
            System.out.println("Sending all commands");
            return recentCommands;
        }

        for (int i = 0; i < commands.size(); i++) {
            if (lastHash.equals(commands.get(i).getCommandHash())) {
                for (int j = i + 1; j < commands.size(); j++) {
                    recentCommands.add(new CommandContainer(correspondingCommTypes.get(j), commands.get(j)));
                }
                break;
            }
        }

        return recentCommands;
    }

    public boolean claimRoute(Player player, Route route, SharedColor color) {
        PlayerInfo playerHand = playerInfo.get(player);
        if (route.getLength() > playerHand.getRemainingTrains()) {
            return false;
        }

        SharedColor routeColor;
        if (route.getColor().equals(SharedColor.RAINBOW)) {
            routeColor = color;
        } else {
            routeColor = route.getColor();
        }

        boolean success = playerHand.playCardsForRouteClaim(route.getLength(), routeColor);
        if (success) {
            playerHand.playTrains(route.getLength());
            if (!finalTurnStarted && playerHand.getRemainingTrains() <= 2) {
                finalTurnStarted = true; //last turn warning
            }

            routeManager.claimRoute(player, route.getRouteId());
            playerHand.addPoints(route.getPoints());
            playerHand.setLongestRoad(routeManager.getLongestRoadLength(player));
            if (routeManager.isLongestRouteChanged()) {
                longestTrainSwitched = true;
                playersWithLongestTrain = routeManager.getPlayersWithLongestRoute();
            }

            return true;
        } else {
            return false;
        }

    }

    public String incrementTurn() {
        nextTurn++;
        if (nextTurn >= orderedPlayers.size()) {
            nextTurn = 0;
        }

        return orderedPlayers.get(nextTurn).getName();
    }

    public String drawTrainCard(Player player) {
        PlayerInfo hand = playerInfo.get(player);
        String randomTrainCardId = trainDeck.drawRandomCard();
        hand.addTrainCard(randomTrainCardId);
        return randomTrainCardId;
    }

    public String drawDestinationCard(Player player) {
        PlayerInfo hand = playerInfo.get(player);
        String randomDesCardId = desDeck.drawRandomCard();
        hand.addDestinationCard(randomDesCardId);
        return randomDesCardId;
    }

    public String selectFaceUpTrainCard(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        String replacementCard = trainDeck.drawFaceUpCard(cardId);
        hand.addTrainCard(cardId);
        return replacementCard;
    }

    public void removeTrainCardFromPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.removeTrainCard(cardId);
    }

    public void removeDestinationCardToPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.removeDestinationCard(cardId);
    }

    public boolean returnDestinationCard(String cardId) {
        desDeck.returnCard(cardId);

        return true;
    }

    public String[] getFaceUpTrainCards() {
        Set<String> faceUpPile = trainDeck.getFaceUpPile();
        return faceUpPile.toArray(new String[0]);
    }

    public boolean isLongestTrainSwitched() {
        return longestTrainSwitched;
    }

    public boolean isFinalTurnStarted() {
        return finalTurnStarted;
    }

    public String[] getPlayerWithLongestTrain() {
        longestTrainSwitched = false;

        String[] players = new String[playersWithLongestTrain.size()];
        for (int i = 0; i < playersWithLongestTrain.size(); i++) {
            players[i] = playersWithLongestTrain.get(i).getName();
        }

        return players;
    }

    public String getPlayerWhoGoesLast() {
        return playerWhoGoesLast;
    }

    public void setCurrentPlayerAsLast() {
        playerWhoGoesLast = orderedPlayers.get(nextTurn).getName();
    }

    public int getLongestRouteLength() {
        PlayerInfo playerHand = playerInfo.get(playersWithLongestTrain.get(0));
        return playerHand.getLongestRoad();
    }

    public String getCurrentPlayer() {
        return orderedPlayers.get(nextTurn).getName();
    }

}
