package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.SwapTrainCardCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class SwapTrainCardCommandData implements ICommandData
{
    public String AuthId;
    public String GameId;
    public String OldTrainCard;
    public String Uuid;
    public SwapTrainCardCommandData(String authId, String gameId, String oldTrainCard){
        this.AuthId = authId;
        this.GameId = gameId;
        this.OldTrainCard = oldTrainCard;
        this.Uuid = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.AuthId + this.Uuid;
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new SwapTrainCardCommand(accessor, this);
    }
}
