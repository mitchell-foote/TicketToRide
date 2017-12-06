package com.example.persistance.pluginInterfaces;

/**
 * Created by Mitchell Foote on 12/5/2017.
 */

public interface IBaseGameSummary
{
    String getKey();
    String getOwnerUsername();
    String getGameName();
    String getPlayer(String number);
    Boolean getStarted();
    String getGameId();
}
