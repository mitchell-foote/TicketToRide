package com.example;

import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.persistance.pluginInterfaces.IPersistanceManagerObject;

import java.util.List;

public class RelationalDatabase implements IPersistanceManagerObject {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<BaseGameSummary> getBaseGames() {
        return null;
    }

    @Override
    public List<IClientCommandData> getCommandList(String gameId) {
        return null;
    }

    @Override
    public String putUser(User user) {
        return null;
    }

    @Override
    public String putBaseGameSummary(BaseGameSummary summary) {
        return null;
    }

    @Override
    public String putCommand(IClientCommandData data, String gameId) {
        return null;
    }

    @Override
    public Void wipeThemOut_AllOfThem() {
        return null;
    }
}
