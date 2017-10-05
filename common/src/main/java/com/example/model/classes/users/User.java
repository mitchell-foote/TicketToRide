package com.example.model.classes.users;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class User extends Player
{
    private String authToken;
    private String password;

    public User(String name, String password, String authToken) {
        super(name);
        this.authToken = authToken;
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        User newUser = (User) object;
        return (newUser.getName().equals(this.getName()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.getName().length(); i++) {
            hash = hash*31 + this.getName().charAt(i);
        }
        return hash;
    }
}
