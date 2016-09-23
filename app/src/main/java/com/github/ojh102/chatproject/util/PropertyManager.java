package com.github.ojh102.chatproject.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.ojh102.chatproject.MyApplication;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public class PropertyManager {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private static final String KEY_ID = "key_id";

    private PropertyManager() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPref.edit();
    }

    // singleton holder pattern : thread safe, lazy class initialization, memory saving.
    private static class InstanceHolder {
        private static final PropertyManager INSTANCE = new PropertyManager();
    }

    public static PropertyManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setId(String id) {
        mEditor.putString(KEY_ID, id);
        mEditor.apply();
    }

    public String getId() {
        return mPref.getString(KEY_ID, "");
    }
}