package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.SetupTrainCardsClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SetupTrainCardsClientCommandData implements IClientCommandData
{
    public String[] TrainCards;
    public String DateString;
    public SetupTrainCardsClientCommandData(String[] trainCards){
        this.TrainCards = trainCards;
        this.DateString = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new SetupTrainCardsClientCommand(accessor,this);
    }
}
