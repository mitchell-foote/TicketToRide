package com.example.fifteam.tickettoride.serverCommunications;

import com.example.communication.IServerAccessor;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.util.List;


/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class ServerProxy implements IServerAccessor
{

    @Override
    public String login(String userName, String password)
    {
        return null;
    }

    @Override
    public String register(String username, String password)
    {
        return null;
    }

    @Override
    public boolean logoff(String authToken)
    {
        return false;
    }

    @Override
    public boolean joinGame(String id, SharedColor color, String authToken)
    {
        return false;
    }

    @Override
    public boolean leaveGame(String id, String authToken)
    {
        return false;
    }

    @Override
    public List<BaseGameSummary> getGames(String authToken)
    {
        return null;
    }
}
