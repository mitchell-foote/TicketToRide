package com.example.gameCommunication.commands.classes.containers;

import com.example.gameCommunication.commands.interfaces.ICommandContainer;
import com.example.gameCommunication.commands.interfaces.ICommandData;

/**
 * Created by Mitchell Foote on 10/18/2017.
 */

public class CommandContainer implements ICommandContainer
{
    private String Type;
    private Object Data;
    public CommandContainer(){};
    public CommandContainer(String type, ICommandData data)
    {
        this.Type = type;
        this.Data = data;
    }
    @Override
    public String getType()
    {
        return Type;
    }

    @Override
    public Object getData()
    {
        return Data;
    }
}
