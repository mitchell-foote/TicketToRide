package com.example.gameCommunication.commands.classes.commandData.client;

import com.example.gameCommunication.commands.classes.fullCommands.client.AddFaceUpTrainCardClientCommand;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.interfaces.IClientCommandAccessor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class AddFaceUpTrainCardClientCommandData implements IClientCommandData
{
    public String Username;
    public String CardId;
    public String NewCardId;
    public String DateString;
    public AddFaceUpTrainCardClientCommandData(String username, String cardId, String newCardId){
        this.Username = username;
        this.CardId = cardId;
        this.NewCardId = newCardId;
        this.DateString = UUID.randomUUID().toString();
    }
    @Override
    public String getCommandHash()
    {
        return this.Username  + "ADDTRN"+ this.DateString;
    }

    @Override
    public IGameCommand makeFullCommandObject(IClientCommandAccessor accessor)
    {
        return new AddFaceUpTrainCardClientCommand(accessor, this);
    }
}
