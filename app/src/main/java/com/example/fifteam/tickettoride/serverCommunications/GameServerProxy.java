package com.example.fifteam.tickettoride.serverCommunications;

import android.graphics.Path;

import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.communication.PathHolder;
import com.example.gameCommunication.commands.classes.CommandSerializationHelper.CommandSerializationHelper;
import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddFaceUpTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.NextTurnClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ReturnDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.SwapTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddDestinationCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddFaceUpTrainCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddTrainCarCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.ClaimRouteCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.EndTurnCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.GetCommandListCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.PostMessageCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.ReturnDestinationCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.SwapTrainCardCommandData;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.enums.CommandTypesEnum;
import com.example.gameModel.interfaces.IGameAccessor;
import com.example.model.enums.SharedColor;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class GameServerProxy implements IGameAccessor
{
    private clientCommunicator connection = new clientCommunicator();
    @Override
    public String drawTrainCard(String authId, String gameId)
    {
        try{
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("drawTrain", new CommandContainer(CommandTypesEnum.AddTrainCard, new AddTrainCarCommandData(authId,gameId))));
        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public List<CommandContainer> getClientCommands(String lastCommandHash, String authId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getGameCommandListURL(),authId, new BaseRequest("CommandListPlz", new CommandContainer(CommandTypesEnum.GetCommands, new GetCommandListCommandData(lastCommandHash, authId, gameId))));
            String listJson = new Gson().toJson(response.response);

            List<CommandContainer> gameList = new ArrayList<>(Arrays.asList(new Gson().fromJson(listJson, CommandContainer[].class)));

            for(CommandContainer cc : gameList){
                CommandSerializationHelper.getInstance().DeSerializeClient(cc, true);
            }
            return gameList;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void postMessage(String message, String authId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("message", new CommandContainer(CommandTypesEnum.PostMessage, new PostMessageCommandData(message,authId,gameId))));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public String drawDestinationCard(String authId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("drawDestination", new CommandContainer(CommandTypesEnum.AddDestination, new AddDestinationCardCommandData(authId,gameId))));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean returnDestinationCard(String authId, String cardId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("returnDestination", new CommandContainer(CommandTypesEnum.ReturnDestinationCard, new ReturnDestinationCardCommandData(authId, cardId, gameId))));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String endTurn(String authId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("endTurn", new CommandContainer(CommandTypesEnum.NextOrEndTurn, new EndTurnCommandData(authId, gameId))));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String addFaceUpTrainCard(String authId, String cardId, String gameId)
    {
        try
        {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(),authId, new BaseRequest("addFaceUpTrain", new CommandContainer(CommandTypesEnum.AddFaceUpTrain, new AddFaceUpTrainCardCommandData(authId, cardId, gameId))));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String swapTrainCard(String authId, String gameId, String oldCardId)
    {
        try{
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(), authId, new BaseRequest("swapTrainCard", new CommandContainer(CommandTypesEnum.SwapTrainCards, new SwapTrainCardCommandData(authId,gameId,oldCardId))));
        } catch (Exception e)
        {
            e.printStackTrace();
            return e.toString();
        }
        return null;
    }

    @Override
    public String claimRoute(String authId, String gameId, String routeId, SharedColor color)
    {
        try {
            BaseResponse response = connection.post(PathHolder.getInstance().getClientHost(), PathHolder.getPort(), PathHolder.getInGameCommandsURL(), authId, new BaseRequest("claimRoute", new CommandContainer(CommandTypesEnum.ClaimRoute, new ClaimRouteCommandData(authId, gameId, routeId, color))));
        } catch (Exception e)
        {
            e.printStackTrace();
            return e.toString();
        }
        return null;
    }
}
