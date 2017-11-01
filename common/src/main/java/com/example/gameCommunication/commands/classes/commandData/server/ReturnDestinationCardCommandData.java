package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.ReturnDestinationCardServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ReturnDestinationCardCommandData implements ICommandData
{
    public String AuthId;
    public String CardId;
    public String GameId;
    public String DateString;
    public ReturnDestinationCardCommandData(String authId, String cardId, String gameId){
        this.AuthId = authId;
        this.CardId = cardId;
        this.GameId = gameId;
        this.DateString = new Date().toString();
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(this.AuthId.hashCode() + this.GameId.hashCode() + this.CardId.hashCode())).toString() + DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new ReturnDestinationCardServerCommand(accessor, this);
    }
}
