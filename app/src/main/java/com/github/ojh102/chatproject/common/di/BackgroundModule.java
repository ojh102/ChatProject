package com.github.ojh102.chatproject.common.di;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

@Module
public class BackgroundModule {

    @Provides
    @Singleton
    @Named("main_thread")
    Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
