package com.example.communication;

import java.nio.file.Path;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class PathHolder
{
    private static PathHolder instance = new PathHolder();
    public static PathHolder getInstance(){
        if(instance == null){
            instance = new PathHolder();
        }
        return instance;
    }
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
    public static String getHost(){ return "10.0.2.2";}
    //public static String getHost() {return "localhost";}
    public static String getPort() {return "30001";}
    private String host = "10.0.2.2";
    public void setHost(String host){
        this.host = host;
    }
    public String getClientHost(){
        return this.host;
    }

}
