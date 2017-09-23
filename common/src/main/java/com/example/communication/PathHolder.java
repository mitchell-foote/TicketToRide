package com.example.communication;

import java.nio.file.Path;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class PathHolder
{
    // Makes it static
    private PathHolder(){}
    public static String getLoginURL()
    {
        return "/login/login";
    }
    public static String getRegisterURL()
    {
        return "/login/register";
    }
    public static String getLogoutURL(){
        return "/login/logOff";
    }
    public static String getGamesURL()
    {
        return "/games/getlist";
    }
    public static String getJoinGameURL()
    {
        return "/games/join";
    }
    public static String getLeaveGameURL()
    {
        return "/games/leave";
    }
    public static String getCreateGameURL()
    {
        return "/games/create";
    }

}
