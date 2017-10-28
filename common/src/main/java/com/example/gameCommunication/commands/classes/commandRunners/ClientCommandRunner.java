package com.example.gameCommunication.commands.classes.commandRunners;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ClientCommandRunner
{
    private IClientCommandAccessor mAccessor;
    public ClientCommandRunner(IClientCommandAccessor accessor){
        this.mAccessor = accessor;
    }
    public void runCommands(List<IClientCommandData> dataList){
        for(IClientCommandData data : dataList){
            data.makeFullCommandObject(this.mAccessor).execute();
        }
    }

}
