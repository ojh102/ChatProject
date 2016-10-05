package com.github.ojh102.chatproject.intro.signin.di;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

public interface SignInPresenter {

    void signIn(String id, String password, String token);


    public interface View {
        void navigateToMain();
        void navigateToSignUp();
        void showToast(String message);
    }
}
