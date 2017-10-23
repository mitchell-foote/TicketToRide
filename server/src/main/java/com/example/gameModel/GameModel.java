package com.example.gameModel;

import com.example.communication.commands.ICommand;
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
    private List<IGameCommand> commands;

    public GameModel(String gameId, Map<String, SharedColor> players) {
        this.gameId = gameId;
        orderedPlayers = new ArrayList<>();
        playerInfo = new HashMap<>();

        for (Map.Entry<String, SharedColor> entry : players.entrySet()) {
            Player player = ServerModel.instance().findPlayerFromToken(entry.getKey());
            orderedPlayers.add(player);
            playerInfo.put(player, new PlayerInfo(entry.getValue()));
        }

        nextTurn = 0;
        desDeck = new DestinationDeck();
        trainDeck = new TrainDeck();
        commands = new ArrayList<IGameCommand>();
    }


    public void addCommand(IGameCommand command) {
        commands.add(command);
    }

    private void incrementTurn() {
        nextTurn++;
        if (nextTurn == orderedPlayers.size()) {
            nextTurn = 0;
        }
    }

    public void addTrainCardToPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.addTrainCard(cardId);
    }

    public void addDestinationCardToPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.addDestinationCard(cardId);
    }

    public void removeTrainCardFromPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.removeTrainCard(cardId);
    }

    public void removeDestinationCardToPlayerHand(Player player, String cardId) {
        PlayerInfo hand = playerInfo.get(player);
        hand.removeDestinationCard(cardId);
    }

}
