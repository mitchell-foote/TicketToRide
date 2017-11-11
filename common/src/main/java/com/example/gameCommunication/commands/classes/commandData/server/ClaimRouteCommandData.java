package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.ClaimRouteCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;
import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class ClaimRouteCommandData implements ICommandData
{
    public String AuthId;
    public String GameId;
    public String RouteId;
    public SharedColor Color;
    public ClaimRouteCommandData(String authId, String gameId, String routeId, SharedColor color){
        this.AuthId = authId;
        this.GameId = gameId;
        this.RouteId = routeId;
        this.Color = color;
    }
    @Override
    public String getCommandHash()
    {
        return "What up fooooooooolz, give me an A+";
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new ClaimRouteCommand(accessor, this);
    }
}
