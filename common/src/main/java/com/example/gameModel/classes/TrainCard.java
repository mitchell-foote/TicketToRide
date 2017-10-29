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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainCard)) return false;

        TrainCard trainCard = (TrainCard) o;

        if (getColor() != trainCard.getColor()) return false;
        return getReferenceId() != null ? getReferenceId().equals(trainCard.getReferenceId()) : trainCard.getReferenceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getColor() != null ? getColor().hashCode() : 0;
        result = 31 * result + (getReferenceId() != null ? getReferenceId().hashCode() : 0);
        return result;
    }
}
