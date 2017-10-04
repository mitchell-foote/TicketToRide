package com.example.fifteam.tickettoride.presenters;

import android.content.Context;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientFacade;
import com.example.fifteam.tickettoride.model.ClientModel;
import com.example.fifteam.tickettoride.views.LoginView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by USER on 10/2/2017.
 */

public class LoginPresenter implements Observer, Toaster {

    LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        ClientFacade.getInstance().addObserver(this);
    }

    public void login(String username, String password) {

        view.displayMessage("This is a login!");
        ClientFacade.getInstance().login(username, password, this);
    }

    public void register(String username, String password) {

        view.displayMessage("This is a register!");
        ClientFacade.getInstance().register(username, password, this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (ClientModel.getInstance().getUser() != null) {
            view.displayMessage("Logged in successfully!");
            view.switchToNextView();
        }
    }

    @Override
    public void displayMessage(String message) {
        view.displayMessage(message);
    }
}
