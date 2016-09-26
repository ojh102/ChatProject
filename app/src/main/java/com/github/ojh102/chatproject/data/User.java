package com.github.ojh102.chatproject.data;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class User {
    private String id;
    private String name;
    private String thumbnail;
    private String token;
    private boolean messageCheck;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public boolean isMessageCheck() {
        return messageCheck;
    }

    public void setMessageCheck(boolean messageCheck) {
        this.messageCheck = messageCheck;
    }

}
