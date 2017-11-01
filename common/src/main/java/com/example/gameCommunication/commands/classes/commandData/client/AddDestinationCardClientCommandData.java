package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.AddDestinationCardClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddDestinationCardClientCommandData implements IClientCommandData
{
    public String Username;
    public String[] CardIds = new String[3];
    public String DateString;
    public AddDestinationCardClientCommandData(String username, String[] cardId){
        this.Username = username;
        this.CardIds = cardId;
        this.DateString = ((Long)new Date().getTime()).toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + "Dest" + this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new AddDestinationCardClientCommand(accessor, this);
    }
}
