package com.example.gameModel;

import com.example.communication.commands.ICommand;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.classes.DestinationDeck;
import com.example.gameModel.classes.TrainDeck;
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
        hand.addTrainCard(replacementCard);
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

}
