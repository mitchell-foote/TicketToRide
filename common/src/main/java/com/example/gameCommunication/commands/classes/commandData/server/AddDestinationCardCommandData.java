package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.AddDestinationCardServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddDestinationCardCommandData implements ICommandData
{

    public String userIdentifier;
    public String gameId;
    public AddDestinationCardCommandData(String userId, String gameId){
        this.userIdentifier = userId;
        this.gameId = gameId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer) (userIdentifier.hashCode() + gameId.hashCode())).toString() + new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new AddDestinationCardServerCommand(accessor, this);
    }
}
