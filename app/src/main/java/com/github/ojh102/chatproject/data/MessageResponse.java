package com.github.ojh102.chatproject.data;

import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-26.
 */

public class MessageResponse {
    private String messageId;
    private String lastMessage;
    private String lastDate;
    private List<User> user;
    private List<MessageData> message;

    public String getMessageId() {
        return messageId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastDate() {
        return lastDate;
    }

    public List<User> getUser() {
        return user;
    }

    public List<MessageData> getMessage() {
        return message;
    }
}
