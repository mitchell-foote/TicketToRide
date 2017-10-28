package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class PostMessageClientCommandData implements IClientCommandData
{
    public String Message;
    public String Username;
    @Override
    public String getCommandHash()
    {
        return null;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return null;
    }
}
