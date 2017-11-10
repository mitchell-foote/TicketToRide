package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.SetupTrainCardsClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SetupTrainCardsClientCommand implements IGameCommand
{

    IClientCommandAccessor mAccessor;
    SetupTrainCardsClientCommandData data;
    public SetupTrainCardsClientCommand(IClientCommandAccessor accessor, SetupTrainCardsClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.setupTrainCards(this.data.TrainCards);
        this.mAccessor.setCommandHash(this.data.getCommandHash());
    }
}
