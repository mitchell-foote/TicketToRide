package com.example.handler;

import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.gameCommunication.commands.classes.CommandSerializationHelper.CommandSerializationHelper;
import com.example.gameCommunication.commands.classes.commandData.server.GetCommandListCommandData;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.gameCommunication.commands.interfaces.ICommandData;
import com.example.gameCommunication.commands.interfaces.IGameCommand;
import com.example.gameModel.GameFacade;
import com.example.gameModel.interfaces.IGameAccessor;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class GetInGameCommandList extends Handler implements HttpHandler
{
    private IGameAccessor serverFacade = new GameFacade();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {
        System.out.println("Command in game handler reached");

        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream reqBody  = httpExchange.getRequestBody();
                OutputStream responseBody = httpExchange.getResponseBody();

                System.out.println(reqBody);
                String reqData = this.readString(reqBody);
                System.out.println(reqData);
                Gson serializer = new Gson();
                BaseRequest data = serializer.fromJson(reqData,BaseRequest.class);

                data.body = serializer.fromJson(serializer.toJson(data.body),CommandContainer.class);
                String auth = httpExchange.getRequestHeaders().getFirst("Authorization");
                data.body = CommandSerializationHelper.getInstance().DeSerializeClient((CommandContainer) data.body,false);
                GetCommandListCommandData commandData =  ((GetCommandListCommandData) ((CommandContainer) data.body).Data);
                CommandContainer[] commandDataList = (CommandContainer[])serverFacade
                        .getClientCommands(commandData.LastCommandHash, commandData.AuthId, commandData.GameId)
                        .toArray();

                BaseResponse response = new BaseResponse();
                response.type = "list";
                response.response = commandDataList;
                response.hasError = false;
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                writeString(new Gson().toJson(response), responseBody);


                responseBody.close();
                httpExchange.close();
            }
            else
            {
                userError(httpExchange, new Exception("Error: wrong request method used"));
            }
        } catch (IOException e) {
            serverError(httpExchange, e);
            e.printStackTrace();
        }
    }
}
