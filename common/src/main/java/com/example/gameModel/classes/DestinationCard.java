package com.example.gameModel.classes;

import com.example.gameModel.enums.City;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DestinationCard
{
    private City FirstCity;
    private City SecondCity;
    private Integer Value;
    private String ReferenceId;

    public DestinationCard(){}
    public DestinationCard(City firstCity, City secondCity, Integer value, String referenceId){
        this.FirstCity = firstCity;
        this.SecondCity = secondCity;
        this.Value = value;
        this.ReferenceId = referenceId;
    }

    public String getReferenceId()
    {
        return this.ReferenceId;
    }

    public City getFirstCity()
    {
        return FirstCity;
    }

    public City getSecondCity()
    {
        return SecondCity;
    }

    public Integer getValue()
    {
        return Value;
    }
}
