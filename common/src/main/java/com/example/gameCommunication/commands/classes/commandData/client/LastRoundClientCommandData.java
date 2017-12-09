package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.LastRoundClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class LastRoundClientCommandData implements IClientCommandData
{
    public String Username;
    public String Uuid;
    public String className;
    public LastRoundClientCommandData(){}
    public LastRoundClientCommandData(String username){
        this.Username = username;
        this.Uuid = UUID.randomUUID().toString();
        this.className = this.getClass().getName();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + this.Uuid;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new LastRoundClientCommand(accessor, this);
    }
    @Override
    public String getClassType()
    {
        return this.getClass().getName();
    }
}
