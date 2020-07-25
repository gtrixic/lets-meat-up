package com.example.letsmeatup;

import java.util.ArrayList;

public class Chat {
    String users;
    String id;
    Message lastMessage;

    public Chat(){}

    public String getUsers(){return this.users;}
    public void setUsers(String users){this.users = users;}
    public String getId(){return this.id;}
    public void setId(String id){this.id = id;}
    public Message getLastMessage(){return this.lastMessage;}
    public void setLastMessage(Message lastMessage){this.lastMessage= lastMessage;}
}
