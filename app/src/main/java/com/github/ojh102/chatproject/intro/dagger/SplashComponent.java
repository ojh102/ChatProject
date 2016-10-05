package com.github.ojh102.chatproject.intro.dagger;

import com.github.ojh102.chatproject.common.dagger.PerActivity;
import com.github.ojh102.chatproject.common.dagger.NetworkComponent;
import com.github.ojh102.chatproject.intro.SplashActivity;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
