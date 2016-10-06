package com.github.ojh102.chatproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-25.
 */

public class MessageRoom implements Serializable{
    private List<User> user;
    private String messageId;
    private String lastId;
    private String lastMessage;
    private String lastDate;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}
