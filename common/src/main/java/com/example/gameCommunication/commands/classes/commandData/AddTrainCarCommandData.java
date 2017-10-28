package com.example.gameCommunication.commands.classes.commandData;

import com.example.gameCommunication.commands.classes.fullCommands.AddTrainCarServerCommand;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCarCommandData implements ICommandData
{
    public String userIdentifier;
    public String gameId;
    public AddTrainCarCommandData(String userId, String gameId){
        this.userIdentifier = userId;
        this.gameId = gameId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer) (userIdentifier.hashCode() + gameId.hashCode())).toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return new AddTrainCarServerCommand(accessor, this);
    }
}
