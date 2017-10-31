package com.example.gameModel.classes;

import com.example.gameModel.enums.City;
import com.example.gameModel.interfaces.ICity;
import com.example.gameModel.interfaces.IRoute;
import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class Route implements IRoute
{
    String routeID;
    SharedColor color;
    int length;
    City endpoint1;
    City endpoint2;
    boolean claimable;

    public Route() {

    }

    public Route(String routeID, SharedColor color, int length, City endpoint1, City endpoint2) {
        this.routeID = routeID;
        this.color = color;
        this.length = length;
        this.endpoint1 = endpoint1;
        this.endpoint2 = endpoint2;
    }

    @Override
    public boolean isClaimable()
    {
        return claimable;
    }

    @Override
    public City getEndpoint1()
    {
        return endpoint1;
    }

    @Override
    public City getEndpoint2()
    {
        return endpoint2;
    }

    @Override
    public SharedColor getColor()
    {
        return color;
    }

    @Override
    public String getRouteId()
    {
        return routeID;
    }

    @Override
    public int getLength()
    {
        return length;
    }
}
