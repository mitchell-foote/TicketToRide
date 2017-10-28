package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddDestinationCardCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddDestinationCardClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private AddDestinationCardClientCommandData mCommandData;
    public AddDestinationCardClientCommand(IClientCommandAccessor accessor, AddDestinationCardClientCommandData data){
        this.mAccessor = accessor;
        this.mCommandData = data;
    }
    @Override
    public void execute()
    {
        mAccessor.drawDestinationCard(mCommandData.Username, mCommandData.CardIds);
    }
}
