package com.github.ojh102.chatproject.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.ojh102.chatproject.common.dagger.NetworkComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(((MyApp)getApplication()).getNetworkComponent());
    }

    protected abstract void setupComponent(NetworkComponent networkComponent);
}