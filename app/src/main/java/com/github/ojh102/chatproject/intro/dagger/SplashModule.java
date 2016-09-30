package com.github.ojh102.chatproject.intro.dagger;

import com.github.ojh102.chatproject.common.dagger.BackgroundModule;
import com.github.ojh102.chatproject.common.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

@Module(includes = {BackgroundModule.class, NetworkModule.class})
public class SplashModule {
    SplashPresenter.View view;

    public SplashModule(SplashPresenter.View view) {
        this.view = view;
    }

    @Provides
    public SplashPresenter provideSplashPresenter(SplashPresenterImpl splashPresenter) {
        return splashPresenter;
    }

    @Provides
    public SplashPresenter.View provideView() {
        return view;
    }
}
