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
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.UndirectedSubgraph;
import org.jgrapht.graph.builder.UndirectedWeightedGraphBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ninjup on 11/10/17.
 */

public class RouteManager {

    private List<Player> players = null;
    private Map<Player, ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge>> graphs = new HashMap<Player, ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge>>();
    private List<Player> playersWithLongestRoutes = new ArrayList<>();
    private Map<Player, Integer> longestRouteLengthsByPlayer = new HashMap<>();

    private int currentLongestRoute = 0;
    private boolean hasChanged = false;


    public RouteManager(List<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            graphs.put(players.get(i), makeGraph());
            longestRouteLengthsByPlayer.put(players.get(i), 0);
        }
    }

    public void claimRoute(Player player, String routeId){
        ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge> playerGraph = graphs.get(player);
        Route routeToClaim = RouteLookupTable.getRouteById(routeId);
        City firstCity = routeToClaim.getEndpoint1();
        City secondCity = routeToClaim.getEndpoint2();

        if (!playerGraph.containsVertex(firstCity)) {
            playerGraph.addVertex(firstCity);
        }

        if (!playerGraph.containsVertex(secondCity)) {
            playerGraph.addVertex(secondCity);
        }

        DefaultWeightedEdge edge = playerGraph.addEdge(firstCity, secondCity);
        playerGraph.setEdgeWeight(edge, routeToClaim.getLength());

        int longestPathForPlayer = calculateLongestRoadForPlayer(player);
        longestRouteLengthsByPlayer.put(player, longestPathForPlayer);
        recalculatePlayersWithLongestRoad();
    }


    private ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge> makeGraph() {
        return new ListenableUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public int calculateDestinationCardBonus(Player player, List<String> desCardIds) {
        ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge> playerGraph = graphs.get(player);
        ConnectivityInspector<City, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(playerGraph);
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
        ListenableUndirectedWeightedGraph<City, DefaultWeightedEdge> playerGraph = graphs.get(player);

        ConnectivityInspector<City, DefaultWeightedEdge> inspector = new ConnectivityInspector<City, DefaultWeightedEdge>(playerGraph);
        List<Set<City>> connectedSets = inspector.connectedSets();

        int largestDiameter = 0;
        for (int i = 0; i < connectedSets.size(); i++) {
            UndirectedSubgraph<City, DefaultWeightedEdge> subgraph = new UndirectedSubgraph<City, DefaultWeightedEdge>(playerGraph, connectedSets.get(i), null);
            FloydWarshallShortestPaths<City, DefaultWeightedEdge> algoPath = new FloydWarshallShortestPaths<>(subgraph);
            int diameter = (int) algoPath.getDiameter();
            if (diameter > largestDiameter) {
                largestDiameter = diameter;
            }
        }

        return largestDiameter;
    }

    private void recalculatePlayersWithLongestRoad() {
        int longestRoute = Collections.max(longestRouteLengthsByPlayer.values());
        if (longestRoute != currentLongestRoute) {
            playersWithLongestRoutes.clear();
            for (Player p : players) {
                if (longestRouteLengthsByPlayer.get(p) == longestRoute) {
                    playersWithLongestRoutes.add(p);
                }
            }
            currentLongestRoute = longestRoute;
            hasChanged = true;
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

    public String getGraphString(Player player) {
        return graphs.get(player).toString() + '\n';
    }
}
