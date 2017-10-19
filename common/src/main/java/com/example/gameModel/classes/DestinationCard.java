package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DestinationCard implements ICard
{
    private String FirstCity;
    private String SecondCity;
    private Integer Value;
    private String ReferenceId;

    public DestinationCard(){}
    public DestinationCard(String firstCity, String secondCity, Integer value, String referenceId){
        this.FirstCity = firstCity;
        this.SecondCity = secondCity;
        this.Value = value;
        this.ReferenceId = referenceId;
    }
    @Override
    public String getReferenceId()
    {
        return this.ReferenceId;
    }

    public String getFirstCity()
    {
        return FirstCity;
    }

    public String getSecondCity()
    {
        return SecondCity;
    }

    public Integer getValue()
    {
        return Value;
    }
}
