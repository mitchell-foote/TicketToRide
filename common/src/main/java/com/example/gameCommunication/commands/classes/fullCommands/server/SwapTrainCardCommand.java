package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.SwapTrainCardCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SwapTrainCardCommand implements IGameCommand
{
    public IGameAccessor mAccessor;
    public SwapTrainCardCommandData data;
    public SwapTrainCardCommand(IGameAccessor accessor, SwapTrainCardCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.swapTrainCard(data.AuthId, data.GameId, data.OldTrainCard);
    }
}
