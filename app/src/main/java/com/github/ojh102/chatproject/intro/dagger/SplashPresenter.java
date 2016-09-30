package com.github.ojh102.chatproject.intro.dagger;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

public interface SplashPresenter {
    void checkLogin(String id);

    interface View {
        void navigateToMain();
        void navigateToIntro();
        void showToast(String message);
    }
}
