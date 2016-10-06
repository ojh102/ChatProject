package com.github.ojh102.chatproject.main.friend.di;

/**
 * Created by OhJaeHwan on 2016-10-06.
 */

public interface FriendPresenter {

    void getData();

    public interface View {
        void refresh();
        void showToast(String message);
    }
}
