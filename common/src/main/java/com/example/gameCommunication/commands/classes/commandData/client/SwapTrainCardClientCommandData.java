package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.SwapTrainCardClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SwapTrainCardClientCommandData implements IClientCommandData
{
    public String DateString;
    public String Username;
    public String OldTrainCard;
    public String NewTrainCard;
    public SwapTrainCardClientCommandData(String username, String oldTrainCard, String newTrainCard){
        this.DateString = UUID.randomUUID().toString();
        this.Username = username;
        this.OldTrainCard = oldTrainCard;
        this.NewTrainCard = newTrainCard;
    }
    @Override
    public String getCommandHash()
    {
        return (this.Username + this.DateString);
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new SwapTrainCardClientCommand(accessor, this);
    }
}
