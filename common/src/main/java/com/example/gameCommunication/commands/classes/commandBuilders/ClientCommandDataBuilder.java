package com.example.gameCommunication.commands.classes.commandBuilders;

import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddFaceUpTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.NextTurnClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ReturnDestinationCardClientCommandData;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameModel.interfaces.IClientCommandDataBuilder;
import com.example.gameModel.interfaces.IGameAccessor;

import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class ClientCommandDataBuilder implements IClientCommandDataBuilder
{


    @Override
    public IClientCommandData drawTrainCard(String username, String cardId)
    {
        return new AddTrainCardClientCommandData(username,cardId);
    }

    @Override
    public IClientCommandData postMessage(String message, String username)
    {
        return new PostMessageClientCommandData(message, username);
    }

    @Override
    public IClientCommandData drawDestinationCard(String username, List<String> cardId)
    {
        return new AddDestinationCardClientCommandData(username, cardId);
    }

    @Override
    public IClientCommandData returnDestinationCard(String username, String cardId)
    {
        return new ReturnDestinationCardClientCommandData(username, cardId);
    }

    @Override
    public IClientCommandData nextTurn(String username)
    {
        return new NextTurnClientCommandData(username);
    }

    @Override
    public IClientCommandData addFaceUpTrainCard(String username, String cardId, String newCardId)
    {
        return new AddFaceUpTrainCardClientCommandData(username, cardId, newCardId);
    }


}
