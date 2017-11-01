package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.PostMessageServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageCommandData implements ICommandData
{
    public String Message;
    public String AuthId;
    public String GameId;
    public String DateString;
    public PostMessageCommandData(String message, String authId, String gameId){
        this.Message = message;
        this.AuthId = authId;
        this.GameId = gameId;
        this.DateString = new Date().toString();
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(Message.hashCode() + AuthId.hashCode() + GameId.hashCode())).toString() + DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new PostMessageServerCommand(accessor, this);
    }
}
