package com.example.gameModel.classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

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

    public static Set<String> getIdStringSet() {
        return MappingTable.keySet();
    }

    //for testing
    public static void main(String[] argv) {
        DestinationDeck deck = new DestinationDeck();

        for (int i = 0; i < 30; i++) {
            DestinationCard card = deck.drawRandomCard();
            System.out.println(card.getFirstCity());
            System.out.println(card.getSecondCity());
            System.out.println(card.getValue());
            System.out.println();
        }
    }
}
