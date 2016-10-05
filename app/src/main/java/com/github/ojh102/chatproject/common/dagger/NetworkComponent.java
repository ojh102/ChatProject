package com.github.ojh102.chatproject.common.dagger;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */
@Singleton
@Component(modules = {AppModule.class, BackgroundModule.class, NetworkModule.class})
public interface NetworkComponent {
    Application application();
    ChatApi chatApi();
    @Named("main_thread") Handler handler();
}
