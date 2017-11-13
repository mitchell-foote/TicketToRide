package com.example.fifteam.tickettoride.model;

import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/12/2017.
 */

public class ClaimRouteParamObj{
    private String routeID;
    private SharedColor color;

    public ClaimRouteParamObj(String routeID, SharedColor color) {
        this.routeID = routeID;
        this.color = color;
    }

    public String getRouteID() {
        return routeID;
    }


    public SharedColor getColor() {
        return color;
    }


}