package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.EndTurnServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class EndTurnCommandData implements ICommandData
{
    public String AuthId;
    public String GameId;
    @Override
    public String getCommandHash()
    {
        return ((Integer)(AuthId.hashCode() + GameId.hashCode())).toString() + new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new EndTurnServerCommand(accessor, this);
    }
}
