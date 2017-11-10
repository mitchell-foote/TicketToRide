package com.example.gameCommunication.commands.classes.fullCommands.client;

import com.example.gameCommunication.commands.classes.commandData.client.LongestTrainRouteSwitchClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 11/9/2017.
 */

public class LongestTrainRouteSwitchClientCommand implements IGameCommand
{
    public IClientCommandAccessor mAccessor;
    public LongestTrainRouteSwitchClientCommandData data;
    public LongestTrainRouteSwitchClientCommand(IClientCommandAccessor accessor, LongestTrainRouteSwitchClientCommandData data){
        this.mAccessor = accessor;
        this.data = data;
    }
    @Override
    public void execute()
    {
        this.mAccessor.longestTrainRouteSwitch(this.data.Username, this.data.LongestLength);
        this.mAccessor.setCommandHash(this.data.getCommandHash());
    }
}
