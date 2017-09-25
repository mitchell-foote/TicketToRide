package com.example.fifteam.tickettoride.serverCommunications;

import com.example.Exceptions.FailedAuthException;
import com.example.Exceptions.FailedJoinException;
import com.example.Exceptions.FailedLeaveException;
import com.example.Exceptions.FailedLoginException;
import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.communication.IServerAccessor;
import com.example.communication.PathHolder;
import com.example.communication.Requests.JoinGameRequest;
import com.example.communication.Requests.LeaveGameRequest;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;

import java.io.IOException;
import java.util.List;


/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class ServerProxy implements IServerAccessor
{
    public clientCommunicator connection = new clientCommunicator();
    @Override
    public String login(String userName, String password) throws Exception
    {
        try{
            BaseResponse response = connection.post(PathHolder.getHost(), PathHolder.getPort(), PathHolder.getLoginURL(),null, new BaseRequest());
            ErrorCheckResponse(response);
            String authToken = null;
            if(response.response instanceof String)
            {
                authToken = (String)response.response;
            }
            return authToken;

        }
        catch(FailedLoginException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    public String register(String username, String password) throws Exception
    {
        try{
            BaseResponse response = connection.post(PathHolder.getHost(), PathHolder.getPort(), PathHolder.getRegisterURL(),null,new BaseRequest());
            ErrorCheckResponse(response);
            String authToken = null;
            if(response.response instanceof String){
                authToken = (String)response.response;
            }
            return authToken;
        }
        catch(FailedLoginException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }


    }

    @Override
    public boolean logoff(String authToken) throws Exception
    {
        try{
            BaseResponse response = connection.post(PathHolder.getHost(), PathHolder.getPort(), PathHolder.getLogoutURL(), authToken, new BaseRequest());
            ErrorCheckResponse(response); // if no error, then logoff was successful.
            return true;
        }
        catch(FailedAuthException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

    @Override
    public boolean joinGame(String id, SharedColor color, String authToken) throws Exception
    {
        JoinGameRequest reqBody = new JoinGameRequest(id,color);
        try{
            BaseResponse response = connection.post(PathHolder.getHost(),PathHolder.getPort(), PathHolder.getJoinGameURL(),authToken,new BaseRequest("join", reqBody));
            ErrorCheckResponse(response);
            return true;
        }
        catch(FailedAuthException e){
            throw e;
        }
        catch(FailedJoinException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

    @Override
    public boolean leaveGame(String id, String authToken) throws Exception
    {
        LeaveGameRequest reqBody = new LeaveGameRequest(id);
        try{
            BaseResponse response = connection.post(PathHolder.getHost(),PathHolder.getPort(), PathHolder.getLeaveGameURL(),authToken,new BaseRequest("join", reqBody));
            ErrorCheckResponse(response);
            return true;
        }
        catch(FailedAuthException e){
            throw e;
        }
        catch(FailedLeaveException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

    @Override
    public List<BaseGameSummary> getGames(String authToken) throws Exception
    {
        try{
            BaseResponse response = connection.get(PathHolder.getHost(), PathHolder.getPort(), PathHolder.getGamesURL(), authToken, new BaseRequest());
            ErrorCheckResponse(response);
            return (List<BaseGameSummary>) response.response;
        }
        catch(FailedAuthException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

    private void ErrorCheckResponse(BaseResponse response) throws Exception
    {
        if(response.hasError){
            if(response.response instanceof FailedLoginException){
                throw (FailedLoginException)response.response;
            }
            else if (response.response instanceof FailedAuthException){
                throw (FailedAuthException)response.response;
            }
            else if(response.response instanceof FailedJoinException){
                throw(FailedJoinException)response.response;
            }
            else if (response.response instanceof FailedLeaveException){
                throw (FailedLeaveException)response.response;
            }
            else if (response.response instanceof IOException){
                throw (IOException)response.response;
            }
            else if (response.response instanceof  Exception){
                throw (Exception)response.response;
            }
            else {
                throw new Exception("Unknown Error occurred");
            }
        }

    }
}
