package com.example.gameCommunication.commands.interfaces;

import com.example.gameCommunication.commands.enums.CommandTypesEnum;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public interface ICommandContainer
{
    CommandTypesEnum getType();
    Object getData();
}
