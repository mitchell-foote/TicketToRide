package com.example.communication;

import com.example.model.classes.login.BaseGameSummary;

import java.awt.Color;
import java.util.List;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public interface IServerAccessor
{
    String login(String userName, String password);
    String register(String username, String password);
    boolean logoff(String authToken);
    boolean joinGame(String id, Color color, String authToken);
    boolean leaveGame(String id, String authToken);
    List<BaseGameSummary> getGames(String authToken);
}
