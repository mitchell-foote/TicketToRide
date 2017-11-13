package com.example.model;

import com.example.gameModel.classes.DestinationCard;
import com.example.gameModel.classes.DestinationLookupTable;
import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.RouteLookupTable;
import com.example.gameModel.enums.City;
import com.example.model.classes.users.Player;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.builder.UndirectedWeightedGraphBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ninjup on 11/10/17.
 */

public class RouteManager {

    private List<Player> players = null;
    private Map<Player, ListenableUndirectedWeightedGraph<City, DefaultEdge>> graphs = new HashMap<Player, ListenableUndirectedWeightedGraph<City, DefaultEdge>>();
    private List<Player> playersWithLongestRoutes = new ArrayList<>();
    private Map<Player, Integer> longestRouteLengthsByPlayer = new HashMap<>();

    private boolean hasChanged = false;


    public RouteManager(List<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            graphs.put(players.get(i), makeGraph());
            longestRouteLengthsByPlayer.put(players.get(i), 0);
        }
    }

    public void claimRoute(Player player, String routeId){
        ListenableUndirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(player);
        Route routeToClaim = RouteLookupTable.getRouteById(routeId);
        City firstCity = routeToClaim.getEndpoint1();
        City secondCity = routeToClaim.getEndpoint2();

        if (!playerGraph.containsVertex(firstCity)) {
            playerGraph.addVertex(firstCity);
        }

        if (!playerGraph.containsVertex(secondCity)) {
            playerGraph.addVertex(secondCity);
        }

        DefaultEdge edge = playerGraph.addEdge(firstCity, secondCity);
        playerGraph.setEdgeWeight(edge, routeToClaim.getLength());

        int longestPathForPlayer = calculateLongestRoadForPlayer(player);
        longestRouteLengthsByPlayer.put(player, longestPathForPlayer);
        recalculatePlayersWithLongestRoad();
    }


    private ListenableUndirectedWeightedGraph<City, DefaultEdge> makeGraph() {
        return new ListenableUndirectedWeightedGraph<>(DefaultEdge.class);
    }

    public int calculateDestinationCardBonus(Player player, List<String> desCardIds) {
        ListenableUndirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(player);
        ConnectivityInspector<City, DefaultEdge> inspector = new ConnectivityInspector<>(playerGraph);
        int scoreBonus = 0;

        for (int i = 0; i < desCardIds.size(); i++) {
            DestinationCard card = DestinationLookupTable.getCardById(desCardIds.get(i));
            City firstCity = card.getFirstCity();
            City secondCity = card.getSecondCity();
            int cardValue = card.getValue();
            if (!playerGraph.containsVertex(firstCity) || !playerGraph.containsVertex(secondCity)) {
                scoreBonus -= cardValue;
                continue;
            }
            if (inspector.pathExists(firstCity, secondCity)) {
                scoreBonus += cardValue;
            } else {
                scoreBonus -= cardValue;
            }
        }

        return scoreBonus;
    }

    public int addLongestRoadBonus(Player player) {
        if (playersWithLongestRoutes.contains(player)) {
            return 10;
        } else {
            return 0;
        }
    }

    private int calculateLongestRoadForPlayer(Player player) {
        ListenableUndirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(player);
        FloydWarshallShortestPaths<City, DefaultEdge> algoPath = new FloydWarshallShortestPaths<>(playerGraph);
        return (int) algoPath.getDiameter();
    }

    private void recalculatePlayersWithLongestRoad() {
        int longestRoute = Collections.max(longestRouteLengthsByPlayer.values());
        playersWithLongestRoutes.clear();
        for (Player p : players) {
            if (longestRouteLengthsByPlayer.get(p) == longestRoute) {
                playersWithLongestRoutes.add(p);
            }
        }
    }

    public boolean isLongestRouteChanged() {
        return hasChanged;
    }

    public List<Player> getPlayersWithLongestRoute() {
        hasChanged = false;
        return playersWithLongestRoutes;
    }

    public int getLongestRoadLength(Player player) {
        return longestRouteLengthsByPlayer.get(player);
    }
}
