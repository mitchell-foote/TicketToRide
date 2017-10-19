package com.example.gameModel.classes;

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
