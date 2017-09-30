package com.example.commands;

import com.example.communication.IServerAccessor;
import com.example.communication.Requests.CreateGameRequest;
import com.example.communication.commands.CommandData;
import com.example.communication.commands.CommandResponse;
import com.example.communication.commands.ICommand;


/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public class CreateGameCommand implements ICommand
{
    private CommandData baseData;
    private CommandResponse response = new CommandResponse();
    private IServerAccessor facade;
    private CreateGameRequest req;
    private String auth;
    public CreateGameCommand(CommandData data, IServerAccessor facade, String auth){
        this.baseData = data;
        this.facade = facade;
        this.auth = auth;
    }
    @Override
    public void execute() throws Exception
    {
        if(baseData.data instanceof CreateGameRequest){
            req = (CreateGameRequest)baseData.data;
            String gameId = facade.createGame(req.name,req.color,this.auth);
            response.response = gameId;
            response.type = "String";
        }
        else {
            response.isError = true;
            response.exception = new Exception("Bad Request Passed In");
            // make an error Response.
        }
        return;
    }

    @Override
    public CommandResponse translate()
    {
        return response;
    }
}
