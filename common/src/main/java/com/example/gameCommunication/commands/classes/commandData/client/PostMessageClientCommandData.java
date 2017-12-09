package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.commandData.server.PostMessageCommandData;
import com.example.gameCommunication.commands.classes.fullCommands.client.PostMessageClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageClientCommandData implements IClientCommandData
{
    public String Message;
    public String Username;
    public String DateString;
    public String className;
    public PostMessageClientCommandData(){};
    public PostMessageClientCommandData(String message, String username){
        this.Message = message;
        this.Username = username;
        this.DateString = UUID.randomUUID().toString();
        this.className = this.getClass().getName();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + "P" + this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new PostMessageClientCommand(accessor, this);
    }
    @Override
    public String getClassType()
    {
        return this.getClass().getName();
    }
}
