package com.example.gameCommunication.commands.classes.fullCommands.server;

import com.example.gameCommunication.commands.classes.commandData.server.EndTurnCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class EndTurnServerCommand implements IGameCommand
{
    private IGameAccessor mAccessor;
    private EndTurnCommandData mData;
    public EndTurnServerCommand(IGameAccessor accessor, EndTurnCommandData data){
        this.mAccessor = accessor;
        this.mData = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.endTurn(mData.AuthId, mData.GameId);
    }
}
