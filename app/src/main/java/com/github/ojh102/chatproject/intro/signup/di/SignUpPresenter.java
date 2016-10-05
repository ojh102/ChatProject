package com.github.ojh102.chatproject.intro.signup.di;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

public interface SignUpPresenter {

    void signUp(String id, String name, String passwd, String confirmPasswd, String token);

    public interface View {
        void navigateToMain();
        void showToast(String message);
    }
}
