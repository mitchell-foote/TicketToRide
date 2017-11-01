package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.ReturnDestinationCardClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ReturnDestinationCardClientCommandData implements IClientCommandData
{
    public String Username;
    public String CardId;
    public String DateString;
    public ReturnDestinationCardClientCommandData(String username, String cardId){
        this.Username = username;
        this.CardId = cardId;
        this.DateString = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username+ "XX" + this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new ReturnDestinationCardClientCommand(accessor, this);
    }
}
