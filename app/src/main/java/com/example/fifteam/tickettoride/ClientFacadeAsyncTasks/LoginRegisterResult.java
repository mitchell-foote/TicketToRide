package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import com.example.model.classes.users.User;

/**
 * Created by samspice on 10/4/17.
 */

public class LoginRegisterResult{
    String errorMessage;
    User newUser;

    public LoginRegisterResult(User newUser) {
        this.newUser = newUser;
    }

    public LoginRegisterResult(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public boolean validResult(){
        if(errorMessage != null){
            return false;
        }
        else return true;
    }
}