package com.github.ojh102.chatproject.data;

import java.io.Serializable;

/**
 * Created by OhJaeHwan on 2016-09-25.
 */

public class MessageData implements Serializable {

    public static String KEY_MESSAGE_RESPONSE = "key_message_response";

    public static final int TYPE_RECEIVE = 3;
    public static final int TYPE_DATE = 2;
    public static final int TYPE_SEND = 1;
    private int type;

    private String fromId;
    private String name;
    private String message;
    private String date;
    private String time;

    public static int getTypeReceive() {
        return TYPE_RECEIVE;
    }

    public static int getTypeDate() {
        return TYPE_DATE;
    }

    public static int getTypeSend() {
        return TYPE_SEND;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
