package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.PostMessageClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageClientCommandData implements IClientCommandData
{
    public String Message;
    public String Username;
    public PostMessageClientCommandData(String message, String username){
        this.Message = message;
        this.Username = username;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(Message.hashCode() + Username.hashCode())).toString() + new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new PostMessageClientCommand(accessor, this);
    }
}
