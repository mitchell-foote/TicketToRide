package com.example.persistance.pluginInterfaces;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;

import java.util.List;

/**
 * Created by Mitchell Foote on 12/5/2017.
 */

public interface IPersistanceManagerObject
{
    List<User> getUsers();
    List<BaseGameSummary> getBaseGames();
    List<IClientCommandData> getCommandList(String gameId);
    String putUser(User user);
    String putBaseGameSummary(BaseGameSummary summary);
    String putCommand(IClientCommandData data, String gameId);
    Void wipeThemOut_AllOfThem();
}
