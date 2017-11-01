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
        return "/login/logoff";
    }
    public static String getGamesURL()
    {
        return "/games/getlist";
    }
    public static String getGameCommandURL()
    {
        return "/games/command";
    }
    public static String getLeaveGameURL()
    {
        return "/games/leave";
    }
    public static String getCreateGameURL(){ return "/games/create"; }
    public static String getStartGameURL(){return "/games/start";}
    public static String getInGameCommandsURL(){return "/ingame/command";}
    public static String getGameCommandListURL(){return "/ingame/comandlist";}
    public static String getHost(){ return "192.168.1.136";}
    public static String getPort() {return "30001";}

}
