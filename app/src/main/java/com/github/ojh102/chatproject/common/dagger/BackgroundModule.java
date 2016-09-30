package com.github.ojh102.chatproject.common.dagger;

import android.os.Handler;
import android.os.Looper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

@Module
public class BackgroundModule {
    @Provides
    public Handler provideMainLooperHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
