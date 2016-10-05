package com.github.ojh102.chatproject.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.ojh102.chatproject.common.di.NetworkComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(((MyApp)getActivity().getApplication()).getNetworkComponent());
    }

    protected abstract void setupComponent(NetworkComponent networkComponent);
}