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
    public static String getHost(){ return "10.0.2.2";}
    public static String getPort() {return "30001";}

}
