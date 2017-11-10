package com.example.model;

import com.example.gameModel.classes.Route;
import com.example.gameModel.classes.RouteLookupTable;
import com.example.gameModel.enums.City;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
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

    List<String> players = null;
    Map<String, DefaultDirectedWeightedGraph<City, DefaultEdge>> graphs;

    public RouteManager(List<String> players) {
        this.players = players;
        for (String s : players) {
            graphs.put(s, makeGraph());
        }
    }

    public void claimRoute(String playerId, String routeId){
        DefaultDirectedWeightedGraph<City, DefaultEdge> playerGraph = graphs.get(playerId);
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
        DefaultDirectedWeightedGraph<City, DefaultEdge> playerGraph = new DefaultDirectedWeightedGraph<City, DefaultEdge>(DefaultEdge.class);

        return playerGraph;
    }


}
