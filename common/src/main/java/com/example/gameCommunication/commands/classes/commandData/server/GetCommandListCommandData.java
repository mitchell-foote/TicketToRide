package com.example.gameCommunication.commands.classes.commandData.server;

import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class GetCommandListCommandData implements ICommandData
{
    public String LastCommandHash;
    public String AuthId;
    public String GameId;
    public GetCommandListCommandData(String lastCommandHash, String authId, String gameId){
        this.LastCommandHash = lastCommandHash;
        this.AuthId = authId;
        this.GameId = gameId;
    }
    @Override
    public String getCommandHash()
    {
        return null;
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return null;
    }
}
