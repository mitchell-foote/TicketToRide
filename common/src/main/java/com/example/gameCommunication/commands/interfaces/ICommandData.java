package com.example.gameCommunication.commands.interfaces;

import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface ICommandData
{
    String getCommandHash();
    IGameCommand makeFullCommandObject(IGameAccessor accessor);

}
