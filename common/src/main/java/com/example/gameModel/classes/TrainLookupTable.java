package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;
import com.example.gameModel.interfaces.ICardLookupTable;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class TrainLookupTable implements ICardLookupTable
{
    public TrainLookupTable(){initLookupTable();}
    private void initLookupTable(){

    }
    @Override
    public ICard getCardById(String cardId)
    {
        return null;
    }
}
