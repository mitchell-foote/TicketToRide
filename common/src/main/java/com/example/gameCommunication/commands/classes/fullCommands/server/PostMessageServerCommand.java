package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.PostMessageCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageServerCommand implements IGameCommand
{
    private IGameAccessor accessor;
    private PostMessageCommandData mData;
    public PostMessageServerCommand(IGameAccessor accessor, PostMessageCommandData data){
        this.accessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        accessor.postMessage(mData.Message, mData.AuthId, mData.GameId);
    }
}
