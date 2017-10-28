package com.example.gameCommunication.commands.classes.fullCommands.server;
import com.example.gameCommunication.commands.classes.commandData.server.AddTrainCarCommandData;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCarServerCommand implements IGameCommand
{
    private IGameAccessor mGameAccessor;
    private ICommandData mCommandData;
    public AddTrainCarServerCommand(IGameAccessor accessor, ICommandData data){
        this.mCommandData = data;
        this.mGameAccessor = accessor;
    }
    @Override
    public void execute()
    {
        mCommandData = (AddTrainCarCommandData)mCommandData;
        mGameAccessor.drawTrainCard(((AddTrainCarCommandData) mCommandData).userIdentifier, ((AddTrainCarCommandData) mCommandData).gameId);
        return;
    }
}
