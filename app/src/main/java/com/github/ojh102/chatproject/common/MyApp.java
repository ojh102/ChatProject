package com.github.ojh102.chatproject.common;

import android.app.Application;
import android.content.Context;

import com.github.ojh102.chatproject.common.dagger.AppModule;
import com.github.ojh102.chatproject.common.dagger.BackgroundModule;
import com.github.ojh102.chatproject.common.dagger.DaggerNetworkComponent;
import com.github.ojh102.chatproject.common.dagger.NetworkComponent;
import com.github.ojh102.chatproject.common.dagger.NetworkModule;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public class MyApp extends Application {

    private static Context mContext;

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .backgroundModule(new BackgroundModule())
                .networkModule(new NetworkModule())
                .build();

    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }

    public static Context getContext() {
        return mContext;
    }
}
