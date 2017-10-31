package com.example.gameModel.classes;

import com.example.gameModel.enums.City;
import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DoubleRoute extends Route
{
    private Route SisterRoute;

    @Override
    public boolean isClaimable(){
        return false;
    }
}
