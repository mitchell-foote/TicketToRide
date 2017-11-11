package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.ClaimRouteCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class ClaimRouteCommand implements IGameCommand
{
    public IGameAccessor mAccessor;
    public ClaimRouteCommandData data;
    public ClaimRouteCommand(IGameAccessor accessor, ClaimRouteCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.claimRoute(data.AuthId, data.GameId, data.RouteId, data.Color);
    }
}
