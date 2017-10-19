package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;
import com.example.gameModel.interfaces.ICardLookupTable;

import java.util.Map;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DestinationLookupTable implements ICardLookupTable
{
    private Map<String, ICard> MappingTable;
    public DestinationLookupTable(){initLookupTable();}

    private void initLookupTable()
    {

    }
    @Override
    public ICard getCardById(String cardId)
    {
        return null;
    }
}
