package com.example.mychat.Model;

public class ChatList extends User {
    public String id;

    public ChatList(String id) {
        this.id = id;
    }

    public ChatList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
