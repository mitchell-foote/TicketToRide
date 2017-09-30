package com.example.communication.Requests;

import com.example.model.enums.SharedColor;

/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public class CreateGameRequest
{
    public String name;
    public SharedColor color;

    public CreateGameRequest(String gameName, SharedColor color)
    {
        this.name = gameName;
        this.color = color;
    }
}
