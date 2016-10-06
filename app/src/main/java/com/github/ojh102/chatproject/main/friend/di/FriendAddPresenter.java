package com.github.ojh102.chatproject.main.friend.di;

import com.github.ojh102.chatproject.model.User;

/**
 * Created by OhJaeHwan on 2016-10-06.
 */

public interface FriendAddPresenter {
    void getSearchFriends(String query);
    void showAddFriendDialog(User user);

    public interface View {
        void refresh();
        void showToast(String message);
        void finish();
    }
}
