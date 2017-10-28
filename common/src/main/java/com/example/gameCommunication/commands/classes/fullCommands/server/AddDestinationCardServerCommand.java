package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.AddDestinationCardCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddDestinationCardServerCommand implements IGameCommand
{
    private IGameAccessor mGameAccessor;
    private AddDestinationCardCommandData mCommandData;
    public AddDestinationCardServerCommand(IGameAccessor accessor, AddDestinationCardCommandData data){
        this.mCommandData = data;
        this.mGameAccessor = accessor;
    }
    @Override
    public void execute()
    {
        mGameAccessor.drawDestinationCard(mCommandData.userIdentifier, mCommandData.gameId);
        return;
    }
}
