package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.ClaimRouteClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class ClaimRouteClientCommandData implements IClientCommandData
{
    public String RouteId;
    public String Username;
    public String DateString;
    public ClaimRouteClientCommandData(String routeId, String username){
        this.RouteId = routeId;
        this.Username = username;
        this.DateString = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new ClaimRouteClientCommand(accessor, this);
    }
}
