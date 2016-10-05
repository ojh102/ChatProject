package com.github.ojh102.chatproject.intro.dagger;

import com.github.ojh102.chatproject.common.dagger.MyScope;
import com.github.ojh102.chatproject.common.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

@Module
public class SplashModule {
    SplashPresenter.View view;

    public SplashModule(SplashPresenter.View view) {
        this.view = view;
    }

    @Provides
    @MyScope
    public SplashPresenter provideSplashPresenter(SplashPresenterImpl splashPresenter) {
        return splashPresenter;
    }

    @Provides
    @MyScope
    public SplashPresenter.View provideView() {
        return view;
    }
}
