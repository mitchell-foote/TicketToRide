package com.example.model;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mitchell Foote on 12/9/2017.
 */

public class JsonPersistanceModel
{
    public Map<String,List<IClientCommandData>> commandList;
    public List<BaseGameSummary> baseGameList;
    public List<User> userList;
    public JsonPersistanceModel(){
        commandList = new HashMap<>();
        baseGameList = new ArrayList<>();
        userList = new ArrayList<>();
    }
}
