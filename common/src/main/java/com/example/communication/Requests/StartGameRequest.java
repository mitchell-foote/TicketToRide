package com.example.communication.Requests;

/**
 * Created by Mitchell Foote on 10/6/2017.
 */

public class StartGameRequest
{
    public StartGameRequest(String id){
        this.gameId = id;
    }

    public String gameId;
}
