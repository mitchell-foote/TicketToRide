package com.example;

import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.model.enums.SharedColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 12/11/2017.
 */

public class RelationalDatabaseTester {

    public static void main(String args[]) {

        RelationalDatabase db = new RelationalDatabase();
        db.wipeThemOut_AllOfThem();

        /*

        User user = new User("TestUser", "password", "auth_token_here");
        db.putUser(user);
        List<User> userList = db.getUsers();

        System.out.println("Users:");
        for (User u : userList) {
            System.out.print(u.getName());
            System.out.print(", ");
            System.out.print(u.getPassword());
            System.out.print(", ");
            System.out.print(u.getAuthToken());
            System.out.print("\n");
        }

        Map<String,SharedColor> playerMap = new HashMap<>();
        playerMap.put("TestUser", SharedColor.GREEN);
        BaseGameSummary summary = new BaseGameSummary("1", "TestUser2", "Cool Game 1", playerMap);
        summary.setStarted(true);
        summary.setFullGameId("fullgameidtest");
        db.putBaseGameSummary(summary);
        List<BaseGameSummary> gameList = db.getBaseGames();

        System.out.println("BaseGameSummaries:");
        for (BaseGameSummary b : gameList) {
            System.out.print(b.getId());
            System.out.print(", ");
            System.out.print(b.getOwner());
            System.out.print(", ");
            System.out.print(b.getGameName());
            System.out.print(", ");
            System.out.print(b.getFullGameId());
            System.out.print(", ");
            System.out.print(b.isStarted());
            System.out.print("\n");
        }

        IClientCommandData data = new PostMessageClientCommandData("Hello", "TestUser");
        db.putCommand(data,"1");
        List<IClientCommandData> dataList = db.getCommandList("1");

        */
    }
}
