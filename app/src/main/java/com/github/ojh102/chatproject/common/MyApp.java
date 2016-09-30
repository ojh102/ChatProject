package com.github.ojh102.chatproject.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public class MyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    public static Context getContext() {
        return mContext;
    }
}
