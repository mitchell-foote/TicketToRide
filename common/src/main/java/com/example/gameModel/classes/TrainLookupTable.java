package com.example.gameModel.classes;

import com.example.gameModel.interfaces.ICard;
import com.example.gameModel.interfaces.ICardLookupTable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

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

    //for testing
    public static void main(String[] argv) {
        TrainCard card = TrainLookupTable.getCardById("09loc");
        System.out.println(card.getColor());
        System.out.println(card.getReferenceId());
    }

}
