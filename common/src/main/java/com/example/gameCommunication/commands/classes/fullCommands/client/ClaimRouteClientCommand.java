package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.ClaimRouteClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class ClaimRouteClientCommand implements IGameCommand
{
    public IClientCommandAccessor mAccessor;
    public ClaimRouteClientCommandData data;
    public ClaimRouteClientCommand(IClientCommandAccessor accessor, ClaimRouteClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.claimRoute(data.Username, data.RouteId, data.Color);
        this.mAccessor.setCommandHash(data.getCommandHash());
    }
}
