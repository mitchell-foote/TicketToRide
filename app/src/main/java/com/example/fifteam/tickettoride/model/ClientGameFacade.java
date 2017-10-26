package com.example.fifteam.tickettoride.model;

/**
 * Created by samks on 10/26/2017.
 */

class ClientGameFacade {
    private static final ClientGameFacade ourInstance = new ClientGameFacade();

    static ClientGameFacade getInstance() {
        return ourInstance;
    }

    private ClientGameFacade() {
    }
}
