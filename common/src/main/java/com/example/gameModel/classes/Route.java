package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICity;
import com.example.gameModel.interfaces.IRoute;
import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class Route implements IRoute
{
    @Override
    public boolean isClaimable()
    {
        return false;
    }

    @Override
    public ICity getEndpoint1()
    {
        return null;
    }

    @Override
    public ICity getEndpoint2()
    {
        return null;
    }

    @Override
    public SharedColor getColor()
    {
        return null;
    }

    @Override
    public String getRouteId()
    {
        return null;
    }

    @Override
    public int getLength()
    {
        return 0;
    }
}
