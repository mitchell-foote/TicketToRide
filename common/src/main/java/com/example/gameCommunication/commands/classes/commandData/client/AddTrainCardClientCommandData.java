package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.AddTrainCarClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCardClientCommandData implements IClientCommandData
{
    public String Username;
    public String CardId;
    public String DateString;
    public String className;
    public AddTrainCardClientCommandData(){}
    public AddTrainCardClientCommandData(String username, String cardId){
        this.Username = username;
        this.CardId = cardId;
        this.DateString = UUID.randomUUID().toString();
        this.className = this.getClass().getName();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + "ADDTRNCRD"+ this.DateString + this.CardId;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new AddTrainCarClientCommand(accessor, this);
    }

    @Override
    public String getClassType()
    {
        return this.getClass().getName();
    }
}
