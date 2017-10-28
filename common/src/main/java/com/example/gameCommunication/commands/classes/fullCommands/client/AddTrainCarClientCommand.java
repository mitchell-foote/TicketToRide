package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.AddTrainCardClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCarClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private AddTrainCardClientCommandData mCommandData;
    public AddTrainCarClientCommand(IClientCommandAccessor accessor, AddTrainCardClientCommandData data){
        this.mAccessor = accessor;
        this.mCommandData = data;
    }
    @Override
    public void execute()
    {
        mAccessor.drawTrainCard(mCommandData.Username, mCommandData.CardId);
        mAccessor.setCommandHash(mCommandData.getCommandHash());
    }
}
