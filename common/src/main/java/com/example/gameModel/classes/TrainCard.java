package com.example.gameModel.classes;

import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class TrainCard
{
    private SharedColor Color;
    private String ReferenceId;

    public TrainCard(){}
    public TrainCard(SharedColor color, String referenceId){
        this.Color = color;
        this.ReferenceId = referenceId;
    }
    public SharedColor getColor(){
        return this.Color;
    }

    public String getReferenceId()
    {
        return this.ReferenceId;
    }
}
