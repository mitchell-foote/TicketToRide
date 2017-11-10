package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.LongestTrainRouteSwitchClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.UUID;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class LongestTrainRouteSwitchClientCommandData implements IClientCommandData
{
    public String Username;
    public String LongestLength;
    public String Uuid;
    public LongestTrainRouteSwitchClientCommandData(String username, String longestLength){
        this.Username = username;
        this.LongestLength = longestLength;
        this.Uuid = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username + this.Uuid;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new LongestTrainRouteSwitchClientCommand(accessor, this);
    }
}
