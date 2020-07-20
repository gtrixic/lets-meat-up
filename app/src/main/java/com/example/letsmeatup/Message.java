package com.example.letsmeatup;

public class Message {
    String message;
    String sender;
    String receiver;
    long createdAt;

    public Message(){}

    public String getMessage(){return this.message;}

    public void SetMessage(String message){this.message = message;}

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

}
