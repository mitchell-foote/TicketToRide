package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.AddTrainCarClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCardClientCommandData implements IClientCommandData
{
    public String Username;
    public String CardId;
    public AddTrainCardClientCommandData(String username, String cardId){
        this.Username = username;
        this.CardId = cardId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(Username.hashCode() + CardId.hashCode())).toString()+ new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new AddTrainCarClientCommand(accessor, this);
    }
}
