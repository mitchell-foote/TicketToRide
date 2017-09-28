package com.example.fifteam.tickettoride.model;

import com.example.model.classes.login.BaseGameSummary;

import java.util.List;

/**
 * A façade for client-side interactions with the data stored in the model, so that other classes
 * need not bother themselves with any of how the model is structured internally, just asking for
 * the info they need and being given it.
 *
 * Created by kcwillmore on 9/28/17.
 */
public class ClientFacade {

    private static ClientModel model = ClientModel.getInstance();

    private void login(String username, String password) {
        //TODO: stub
    }

    private void register(String username, String password) {
        //TODO: stub
    }

    private void logout() {
        //TODO: stub
    }

    private List<BaseGameSummary> getGamesList() {
        return model.getGamesList();
    }

    private void createGame() {
        //TODO: stub
    }

    private void joinGame(String gameID) {
        //TODO: stub
    }

    private void leaveGame() {
        //TODO: stub
    }
}
