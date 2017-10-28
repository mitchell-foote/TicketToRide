package com.example.gameCommunication.commands.classes.commandData;

import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IGameAccessor;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddTrainCarClientCommandData implements ICommandData
{
    public String Username;
    public String CardId;
    public AddTrainCarClientCommandData(String username, String cardId){
        this.Username = username;
        this.CardId = cardId;
    }
    @Override
    public String getCommandHash()
    {
        return ((Integer)(Username.hashCode() + CardId.hashCode())).toString();
    }

    @Override
    public IGameCommand makeFullCommandObject(IGameAccessor accessor)
    {
        return null;
    }
}
