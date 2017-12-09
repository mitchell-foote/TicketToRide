package com.example;

import com.example.gameCommunication.commands.classes.commandData.client.ClientCommandDeserializer;
import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.JsonPersistanceModel;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;
import com.example.persistance.pluginInterfaces.IPersistanceManagerObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mitchell Foote on 12/9/2017.
 */

public class JsonPersistanceManagement implements IPersistanceManagerObject
{
    public String fileName;
    public JsonPersistanceModel model;
    public JsonPersistanceManagement(){
        this.fileName = "json.txt";
        model = new JsonPersistanceModel();
        loadFile();
    }
    private void loadFile(){
        String line = new String();
        StringBuilder builder = new StringBuilder();
        GsonBuilder builder1 = new GsonBuilder();

        builder1.registerTypeAdapter(IClientCommandData.class,new ClientCommandDeserializer<>());
        Gson gson = builder1.create();
        try {

            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
            String data = builder.toString();
            model = gson.fromJson(data,JsonPersistanceModel.class);
            model.baseGameList = gson.fromJson(gson.toJson(model.baseGameList),new TypeToken<List<BaseGameSummary>>(){}.getType());
            for(String key : model.commandList.keySet()){
                List<IClientCommandData> listData = gson.fromJson(gson.toJson(model.commandList.get(key)),new TypeToken<List<IClientCommandData>>(){}.getType());
                model.commandList.put(key,listData);

            }
            for(BaseGameSummary summary : model.baseGameList){
                summary.players = gson.fromJson(gson.toJson(summary.players), new TypeToken<Map<String,SharedColor>>(){}.getType());
                SharedColor color = summary.players.get("Batman");
                String wait = "WAIT!";
            }
            String wait = "WAIT!";
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open or find file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    private void saveFile(){
        String text = new Gson().toJson(this.model);
        try {
            FileWriter fileWriter =
                    new FileWriter(fileName, false);
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write(text);


            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
        }
    }
    @Override
    public List<User> getUsers()
    {
        return model.userList;
    }

    @Override
    public List<BaseGameSummary> getBaseGames()
    {
        return model.baseGameList;
    }

    @Override
    public List<IClientCommandData> getCommandList(String gameId)
    {
        return model.commandList.get(gameId);
    }

    @Override
    public String putUser(User user)
    {
        model.userList.add(user);
        saveFile();
        return null;
    }

    @Override
    public String putBaseGameSummary(BaseGameSummary summary)
    {
        model.baseGameList.add(summary);
        saveFile();
        return null;
    }

    @Override
    public String putCommand(IClientCommandData data, String gameId)
    {
        if(model.commandList.containsKey(gameId)){
            model.commandList.get(gameId).add(data);
        }
        else {
            List<IClientCommandData> list = new ArrayList<>();
            list.add(data);
            model.commandList.put(gameId,list);
        }
        saveFile();
        return null;
    }

    @Override
    public Void wipeThemOut_AllOfThem()
    {
        model = new JsonPersistanceModel();
        saveFile();
        return null;
    }


}
