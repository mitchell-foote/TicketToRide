package com.example.gameCommunication.commands.interfaces;

import com.example.gameModel.interfaces.IClientCommandAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public interface IClientCommandData
{
    String getCommandHash();
    IGameCommand makeFullCommandObject(IClientCommandAccessor accessor);

}
