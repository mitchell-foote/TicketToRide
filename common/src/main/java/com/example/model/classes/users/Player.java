package com.example.model.classes.users;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class Player
{
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Player newPlayer = (Player) object;
        return (newPlayer.getName().equals(this.getName()));
    }
}
