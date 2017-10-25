package com.example.gameModel.classes;

/**
 * Created by samks on 10/24/2017.
 */

public class ChatEntry {
    private String name;
    private String content;

    public ChatEntry(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
