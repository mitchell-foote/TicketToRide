package com.example.handler;

import com.example.commands.CreateGameCommand;
import com.example.commands.JoinGameCommand;
import com.example.commands.LeaveGameCommand;
import com.example.communication.IServerAccessor;
import com.example.communication.commands.CommandData;
import com.example.communication.commands.CommandResponse;
import com.example.communication.commands.ICommand;
import com.example.model.ServerFacade;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public class CreateJoinLeaveCommandHandler extends Handler implements HttpHandler
{
    private IServerAccessor facade = new ServerFacade();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {
        boolean success  = false;
        try{
            if(httpExchange.getRequestMethod().toLowerCase().equals("post")){
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                InputStream reqBody  = httpExchange.getRequestBody();
                System.out.println(reqBody);
                String reqData = this.readString(reqBody);
                System.out.println(reqData);
                Gson serializer = new Gson();
                CommandData data = serializer.fromJson(reqData,CommandData.class);
                String auth = httpExchange.getRequestHeaders().getFirst("Authorization");
                ICommand command;
                switch(data.type){
                    case "create":{
                        command = new CreateGameCommand(data,facade,auth);
                        break;
                    }
                    case "join": {
                        command = new JoinGameCommand(data,facade,auth);
                        break;
                    }
                    case "leave": {
                        command = new LeaveGameCommand(data,facade,auth);
                        break;
                    }
                    default:{
                        throw new Exception("Bad Command type");
                    }
                }
                command.execute();
                CommandResponse response = command.translate();
                this.returnCommandResponse(httpExchange,response,data.type);

            }
        } catch (Exception e)
        {
            e.printStackTrace();
            this.userError(httpExchange,e);
        }
    }
}
