package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.AddFaceUpTrainCardCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddFaceUpTrainCardServerCommand implements IGameCommand
{
    private IGameAccessor mAccessor;
    private AddFaceUpTrainCardCommandData mData;
    public AddFaceUpTrainCardServerCommand(IGameAccessor accessor, AddFaceUpTrainCardCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.addFaceUpTrainCard(mData.AuthId, mData.CardId, mData.GameId);
    }
}
