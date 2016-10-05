package com.github.ojh102.chatproject.main.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.intro.IntroActivity;
import com.github.ojh102.chatproject.util.PropertyManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {

    @BindView(R.id.switchAlarm)
    SwitchCompat switchAlram;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        switchAlram.setChecked(PropertyManager.getInstance().getAlram());
        switchAlram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PropertyManager.getInstance().setAlram(isChecked);
                if(isChecked) {
                    Toast.makeText(getContext(), "푸쉬 진동 설정 ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "푸쉬 진동 설정 OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void onClickBack() {
        PropertyManager.getInstance().clear();
        Intent intent = new Intent(getContext(), IntroActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
