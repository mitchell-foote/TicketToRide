package com.example.gameModel.classes;

import com.example.gameModel.classes.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samks on 10/24/2017.
 */

public class MapSummary {
    private Map<String, Route> routes;

    public MapSummary(){
        this.routes = new HashMap<>();
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route toAdd){
        String key = toAdd.getRouteId();
        this.routes.put(key,toAdd);
    }

    public Route getRoute(String id){
        return this.routes.get(id);
    }
}
