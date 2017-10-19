package com.example.gameModel.interfaces;

import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface IRoute
{
    boolean isClaimable();
    ICity getEndpoint1();
    ICity getEndpoint2();
    SharedColor getColor();
    String getRouteId();
    int getLength();
}
