package com.example.communication;

import com.example.Exceptions.FailedLoginException;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.util.List;


/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public interface IServerAccessor
{
    String login(String userName, String password) throws Exception;
    String register(String username, String password) throws Exception;
    boolean logoff(String authToken) throws Exception;
    boolean joinGame(String id, SharedColor color, String authToken) throws Exception;
    boolean leaveGame(String id, String authToken) throws Exception;
    List<BaseGameSummary> getGames(String authToken) throws Exception;
    String createGame(String gameName, SharedColor color, String authToken) throws Exception;

}
