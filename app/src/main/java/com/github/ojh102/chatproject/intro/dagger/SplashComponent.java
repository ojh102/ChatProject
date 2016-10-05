package com.github.ojh102.chatproject.intro.dagger;

import com.github.ojh102.chatproject.common.dagger.MyScope;
import com.github.ojh102.chatproject.common.dagger.NetworkComponent;
import com.github.ojh102.chatproject.intro.SplashActivity;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */
@MyScope
@Component(dependencies = NetworkComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
