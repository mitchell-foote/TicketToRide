package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;
import com.example.gameModel.interfaces.ICardLookupTable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class DestinationLookupTable {

    private static Map<String, DestinationCard> MappingTable;
    private DestinationLookupTable(){}

    static {
        Type type = new TypeToken<Map<String, DestinationCard>>(){}.getType();
        MappingTable = new Gson().fromJson(JsonHolder.getDestinationJson(), type);
    }

    public static DestinationCard getCardById(String cardId) {
        return MappingTable.get(cardId);
    }

    //for testing
    public static void main(String[] argv) {
        DestinationCard card = DestinationLookupTable.getCardById("25d");
        System.out.println(card.getFirstCity());
        System.out.println(card.getSecondCity());
        System.out.println(card.getValue());
    }
}
