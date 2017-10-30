package com.example.fifteam.tickettoride.model;

/**
 * Created by samks on 10/30/2017.
 */

class ClientGamePresenterFacade {
    private static final ClientGamePresenterFacade ourInstance = new ClientGamePresenterFacade();

    static ClientGamePresenterFacade getInstance() {
        return ourInstance;
    }

    private ClientGamePresenterFacade() {
    }
}
