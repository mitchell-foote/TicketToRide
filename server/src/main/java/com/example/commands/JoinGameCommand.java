package com.example.commands;

import com.example.communication.IServerAccessor;
import com.example.communication.Requests.JoinGameRequest;
import com.example.communication.commands.CommandData;
import com.example.communication.commands.CommandResponse;
import com.example.communication.commands.ICommand;
import com.google.gson.Gson;


/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public class JoinGameCommand implements ICommand
{
    private CommandData baseData;
    private CommandResponse response = new CommandResponse();
    private IServerAccessor facade;
    private JoinGameRequest req;
    private String auth;
    public JoinGameCommand(CommandData data, IServerAccessor facade, String auth){
        this.baseData = data;
        this.facade = facade;
        this.auth = auth;
    }
    @Override
    public void execute() throws Exception
    {
        //baseData.data = new Gson().fromJson(baseData.toString(), JoinGameRequest.class);
        if(this.baseData.data instanceof JoinGameRequest){
            req = (JoinGameRequest)baseData.data;
            boolean success = facade.joinGame(req.id, req.color, this.auth);
            if(success){
                response.isError = false;
                response.response = Boolean.valueOf(true);
                response.type = "Boolean";
            }
        }
        return;
    }

    @Override
    public CommandResponse translate()
    {
        return response;
    }
}
