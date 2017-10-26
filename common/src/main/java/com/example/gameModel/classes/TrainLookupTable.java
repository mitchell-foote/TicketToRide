package com.example.gameModel.classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
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
        MappingTable = Collections.unmodifiableMap(MappingTable);
    }

    public static TrainCard getCardById(String cardId) {
        return MappingTable.get(cardId);
    }

    public static Set<String> getIdStringSet() {
        return new HashSet<>(MappingTable.keySet());
    }

    //for testing
    public static void main(String[] argv) {
        TrainDeck deck = new TrainDeck();

        for (int i = 0; i < 105; i++) {
            String cardId = deck.drawRandomCard();
            TrainCard card = getCardById(cardId);
            System.out.println(card.getColor());
            System.out.println();
        }
    }

}
