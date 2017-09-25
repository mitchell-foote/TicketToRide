package com.example.communication.Requests;

import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 9/24/2017.
 */
public class JoinGameRequest
{
    public String id;
    public SharedColor color;
    public JoinGameRequest(String id, SharedColor color){
        this.id = id;
        this.color = color;
    }
}
