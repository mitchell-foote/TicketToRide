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
    public List<String> CardIds;
    public AddDestinationCardClientCommandData(String username, List<String> cardId){
        this.Username = username;
        this.CardIds = cardId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(Username.hashCode() + CardIds.hashCode())).toString() + new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new AddDestinationCardClientCommand(accessor, this);
    }
}
