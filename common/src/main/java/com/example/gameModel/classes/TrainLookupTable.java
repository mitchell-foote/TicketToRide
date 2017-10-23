package com.example.gameModel.classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class TrainLookupTable
{
    private static Map<String, TrainCard> MappingTable;
    private TrainLookupTable() {}

    static {
        Type type = new TypeToken<Map<String, TrainCard>>(){}.getType();
        MappingTable = new Gson().fromJson(JsonHolder.getTrainCardJson(), type);
    }

    public static TrainCard getCardById(String cardId) {
        return MappingTable.get(cardId);
    }

    public static Set<String> getIdStringSet() {
        return MappingTable.keySet();
    }

    //for testing
    public static void main(String[] argv) {
        TrainDeck deck = new TrainDeck();

        for (int i = 0; i < 105; i++) {
            TrainCard card = deck.drawRandomCard();
            System.out.println(card.getColor());
            System.out.println();
        }
    }

}
