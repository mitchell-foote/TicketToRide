package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageClientCommand implements IGameCommand
{
    private IClientCommandAccessor mAccessor;
    private PostMessageClientCommandData mData;
    public PostMessageClientCommand(IClientCommandAccessor accessor, PostMessageClientCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        mAccessor.postMessage(mData.Message, mData.Username);
        mAccessor.setCommandHash(mData.getCommandHash());
    }
}
