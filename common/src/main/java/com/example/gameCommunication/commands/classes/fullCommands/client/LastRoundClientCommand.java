package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.LastRoundClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class LastRoundClientCommand implements IGameCommand
{
    public LastRoundClientCommandData data;
    public IClientCommandAccessor mAccessor;
    public LastRoundClientCommand(IClientCommandAccessor accessor, LastRoundClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.lastRound(data.Username);
        this.mAccessor.setCommandHash(data.getCommandHash());
    }
}
