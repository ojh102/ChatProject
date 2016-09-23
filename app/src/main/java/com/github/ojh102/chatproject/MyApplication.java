package com.github.ojh102.chatproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public class MyApplication extends Application {
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
