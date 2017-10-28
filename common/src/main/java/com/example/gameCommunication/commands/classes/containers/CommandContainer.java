package com.example.gameCommunication.commands.classes.containers;

import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameCommunication.commands.interfaces.ICommandData;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class CommandContainer implements ICommandContainer
{
    public CommandTypesEnum Type;
    public Object Data;
    public CommandContainer(){};
    public CommandContainer(CommandTypesEnum type, Object data)
    {
        this.Type = type;
        this.Data = data;
    }
    @Override
    public CommandTypesEnum getType()
    {
        return Type;
    }

    @Override
    public Object getData()
    {
        return Data;
    }
}
