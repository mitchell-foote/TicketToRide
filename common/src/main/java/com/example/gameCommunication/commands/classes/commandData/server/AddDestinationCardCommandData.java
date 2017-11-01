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
    public String DateString;
    public AddDestinationCardCommandData(String userId, String gameId){
        this.userIdentifier = userId;
        this.gameId = gameId;
        this.DateString = new Date().toString();
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer) (userIdentifier.hashCode() + gameId.hashCode())).toString() + this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new AddDestinationCardServerCommand(accessor, this);
    }
}
