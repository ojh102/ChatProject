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
    private static final String KEY_NAME = "key_name";
    private static final String KEY_ALRAM = "key_alram";

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

    public void setName(String name) {
        mEditor.putString(KEY_NAME, name);
        mEditor.apply();
    }

    public String getname() {
        return mPref.getString(KEY_NAME, "");
    }

    public void setAlram(boolean alram) {
        mEditor.putBoolean(KEY_ALRAM, alram);
        mEditor.apply();
    }

    public boolean getAlram() {
        return mPref.getBoolean(KEY_ALRAM, true);
    }

    public void clear() {
        mEditor.clear();
        mEditor.apply();
    }


}
