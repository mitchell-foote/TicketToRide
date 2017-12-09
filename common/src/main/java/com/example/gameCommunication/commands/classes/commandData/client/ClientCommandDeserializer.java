package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

/**
 * Created by Mitchell Foote on 12/9/2017.
 */

public class ClientCommandDeserializer<T extends IClientCommandData> implements JsonDeserializer<T>{
    private static final String CLASSNAME = "className";


    @SuppressWarnings("unchecked")
    public Class<T> getClassInstance(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException(cnfe.getMessage());
        }
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        final String className = prim.getAsString();
        final Class<T> clazz = getClassInstance(className);
        T response =jsonDeserializationContext.deserialize(jsonObject, clazz);
        return response;
    }
}

