package com.github.ojh102.chatproject.splash.di;

import com.github.ojh102.chatproject.common.di.PerActivity;

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
    @PerActivity
    public SplashPresenter provideSplashPresenter(SplashPresenterImpl splashPresenter) {
        return splashPresenter;
    }

    @Provides
    @PerActivity
    public SplashPresenter.View provideView() {
        return view;
    }
}
