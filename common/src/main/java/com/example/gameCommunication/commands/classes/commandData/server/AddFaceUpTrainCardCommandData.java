package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.classes.fullCommands.server.AddFaceUpTrainCardServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.Date;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddFaceUpTrainCardCommandData implements ICommandData
{
    public String AuthId;
    public String CardId;
    public String GameId;
    public AddFaceUpTrainCardCommandData(String authId, String cardId, String gameId){
        this.AuthId = authId;
        this.CardId = cardId;
        this.GameId = gameId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(this.AuthId.hashCode() + this.CardId.hashCode() + this.GameId.hashCode())).toString() + new Date().toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new AddFaceUpTrainCardServerCommand(accessor, this);
    }
}
