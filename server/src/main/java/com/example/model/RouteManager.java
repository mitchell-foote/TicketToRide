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
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.builder.UndirectedWeightedGraphBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by ninjup on 11/10/17.
 */

public class RouteManager {

    List<Player> players = null;
    Map<Player, DefaultDirectedWeightedGraph<City, DefaultEdge>> graphs;

    public RouteManager(List<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            graphs.put(players.get(i), makeGraph());
        }
    }

    public void claimRoute(Player player, String routeId){
        DefaultDirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(player);
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
    }


    public DefaultDirectedWeightedGraph<City, DefaultEdge> makeGraph() {
        return new DefaultDirectedWeightedGraph<City, DefaultEdge>(DefaultEdge.class);
    }

    public int calculateDestinationCardBonus(Player player, List<String> desCardIds) {
        DefaultDirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(player);
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


}
