package com.github.ojh102.chatproject.splash.di;

import com.github.ojh102.chatproject.common.di.PerActivity;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.splash.SplashActivity;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
