package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.EndGameClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class EndGameClientCommand implements IGameCommand
{
    public IClientCommandAccessor mAccessor;
    public EndGameClientCommandData data;
    public EndGameClientCommand(IClientCommandAccessor accessor, EndGameClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.endGame(data.FinalScores);
        this.mAccessor.setCommandHash(this.data.getCommandHash());
    }
}
