package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.NextTurnClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class NextTurnClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private NextTurnClientCommandData mData;
    public NextTurnClientCommand(IClientCommandAccessor accessor, NextTurnClientCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.nextTurn(mData.Username);
        this.mAccessor.setCommandHash(mData.getCommandHash());
    }
}
