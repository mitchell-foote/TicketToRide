package com.example;

import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run
{
    public static void main(String args[]){
        JsonPersistanceManagement mgr = new JsonPersistanceManagement();
        User user = new User("Batman", "Batman","Batman");
        mgr.putUser(user);
        List<User> userList = mgr.getUsers();
        Map<String,SharedColor> map = new HashMap<>();
        map.put("Batman", SharedColor.BLACK);
        BaseGameSummary summary = new BaseGameSummary("1","1","1",map);
        mgr.putBaseGameSummary(summary);
        List<BaseGameSummary> gameList = mgr.getBaseGames();
        IClientCommandData data = new PostMessageClientCommandData("sup", "Batman");
        mgr.putCommand(data,"1");
        List<IClientCommandData> dataList = mgr.getCommandList("1");
    }

}
