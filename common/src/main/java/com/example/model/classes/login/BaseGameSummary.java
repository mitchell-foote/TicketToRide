package com.example.model.classes.login;

import com.example.model.classes.users.Player;

import java.awt.Color;

import java.util.Map;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class BaseGameSummary
{
    public String id;
    public String ownerUsername;
    public String GameName;
    public Map<Player, Color> players;
}
