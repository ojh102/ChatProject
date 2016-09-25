package com.github.ojh102.chatproject.data;

import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-25.
 */

public class MessageRoom {
    private List<User> user;
    private String messageId;
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
