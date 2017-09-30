package com.example.commands;

import com.example.Exceptions.FailedLeaveException;
import com.example.communication.IServerAccessor;
import com.example.communication.Requests.LeaveGameRequest;
import com.example.communication.commands.CommandData;
import com.example.communication.commands.CommandResponse;
import com.example.communication.commands.ICommand;

/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public class LeaveGameCommand implements ICommand
{
    private CommandData baseData;
    private CommandResponse response = new CommandResponse();
    private IServerAccessor facade;
    private LeaveGameRequest req;
    private String auth;
    public LeaveGameCommand(CommandData data, IServerAccessor facade, String auth){
        this.baseData = data;
        this.facade = facade;
        this.auth = auth;
    }
    @Override
    public void execute() throws Exception
    {
        if(baseData.data instanceof LeaveGameRequest){
            this.req = (LeaveGameRequest)baseData.data;
            boolean success = facade.leaveGame(req.id,this.auth);
            if(success){
                response.type = "Boolean";
                response.isError = false;
                response.response = Boolean.valueOf(true);
            }
            throw new FailedLeaveException("Could Not leave game");
        }
        return;
    }

    @Override
    public CommandResponse translate()
    {
        return response;
    }
}
