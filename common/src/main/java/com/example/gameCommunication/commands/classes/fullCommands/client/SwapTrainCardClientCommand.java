package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.SwapTrainCardClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SwapTrainCardClientCommand implements IGameCommand
{
    public IClientCommandAccessor mAccessor;
    public SwapTrainCardClientCommandData data;
    public SwapTrainCardClientCommand(IClientCommandAccessor accessor, SwapTrainCardClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.swapTrainCards(data.Username, data.OldTrainCard, data.NewTrainCard);
        this.mAccessor.setCommandHash(data.getCommandHash());
    }
}
