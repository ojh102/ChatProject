package com.github.ojh102.chatproject.main.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.intro.IntroActivity;
import com.github.ojh102.chatproject.intro.SignInFragment;
import com.github.ojh102.chatproject.util.PropertyManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnBack)
    public void onClickBack() {
        PropertyManager.getInstance().clear();
        Intent intent = new Intent(getContext(), IntroActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
