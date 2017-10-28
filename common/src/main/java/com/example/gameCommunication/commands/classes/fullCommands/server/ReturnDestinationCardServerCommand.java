package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.ReturnDestinationCardCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ReturnDestinationCardServerCommand implements IGameCommand
{
    private IGameAccessor mAccessor;
    private ReturnDestinationCardCommandData mData;
    public ReturnDestinationCardServerCommand(IGameAccessor accessor, ReturnDestinationCardCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        mAccessor.returnDestinationCard(mData.AuthId, mData.CardId, mData.GameId);
    }
}
