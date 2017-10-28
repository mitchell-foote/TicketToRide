package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.AddFaceUpTrainCardClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddFaceUpTrainCardClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private AddFaceUpTrainCardClientCommandData mData;
    public AddFaceUpTrainCardClientCommand(IClientCommandAccessor accessor, AddFaceUpTrainCardClientCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.addFaceUpTrainCard(mData.Username, mData.CardId, mData.NewCardId);
        this.mAccessor.setCommandHash(mData.getCommandHash());
    }
}
