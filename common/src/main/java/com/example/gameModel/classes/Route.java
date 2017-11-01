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
    boolean doubleRoute;
    Route sisterRoute;
    SharedColor ownerColor;

    public Route() {

    }

    public Route(String routeID, SharedColor color, int length, City endpoint1, City endpoint2) {
        this.routeID = routeID;
        this.color = color;
        this.length = length;
        this.endpoint1 = endpoint1;
        this.endpoint2 = endpoint2;
        claimable = true;
        doubleRoute = false;
        sisterRoute = null;
        ownerColor = SharedColor.RAINBOW;
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

    public void setDoubleRoute(boolean doubleRoute) {
        this.doubleRoute = doubleRoute;
    }

    public boolean isDoubleRoute() {
        return doubleRoute;
    }

    public void setSisterRoute(Route r) {
        sisterRoute = r;
    }

    public Route getSisterRoute() {
        return sisterRoute;
    }

    public void setClaimed(SharedColor ownerColor) {
        claimable = false;
        this.ownerColor = ownerColor;
    }

    public SharedColor getOwnerColor() {
        return ownerColor;
    }

    public int getPoints() {

        int point_value = 0;

        switch (length) {
            case 1: {
                point_value = 1;
                break;
            }
            case 2: {
                point_value = 2;
                break;
            }
            case 3: {
                point_value = 4;
                break;
            }
            case 4: {
                point_value = 7;
                break;
            }
            case 5: {
                point_value = 10;
                break;
            }
            case 6: {
                point_value = 15;
                break;
            }
        }

        return point_value;
    }
}
