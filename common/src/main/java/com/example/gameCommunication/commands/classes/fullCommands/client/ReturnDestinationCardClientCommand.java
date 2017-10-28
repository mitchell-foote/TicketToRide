package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.ReturnDestinationCardClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ReturnDestinationCardClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private ReturnDestinationCardClientCommandData mData;
    public ReturnDestinationCardClientCommand(IClientCommandAccessor accessor, ReturnDestinationCardClientCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.returnDestinationCard(mData.Username, mData.CardId);
        this.mAccessor.setCommandHash(mData.getCommandHash());
    }
}
