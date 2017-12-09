package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.EndGameClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.classes.PlayerScoreContainer;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class EndGameClientCommandData implements IClientCommandData
{
    public PlayerScoreContainer[] FinalScores;
    public String Uuid;
    public String className;
    public EndGameClientCommandData(){}
    public EndGameClientCommandData(PlayerScoreContainer[] finalScores){
        this.FinalScores = finalScores;
        this.Uuid = UUID.randomUUID().toString();
        this.className = this.getClass().getName();
    }
    @Override
    public String getCommandHash()
    {
        return this.Uuid;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new EndGameClientCommand(accessor, this);
    }

    @Override
    public String getClassType()
    {
        return this.getClass().getName();
    }
}
