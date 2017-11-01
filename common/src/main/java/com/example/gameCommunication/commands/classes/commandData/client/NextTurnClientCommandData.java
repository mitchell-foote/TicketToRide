package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.NextTurnClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class NextTurnClientCommandData implements IClientCommandData
{
    public String Username;
    public String DateString;
    public NextTurnClientCommandData(String username){
        this.Username = username;
        DateString = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username +"NXT"+ this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new NextTurnClientCommand(accessor, this);
    }
}
