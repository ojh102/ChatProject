package com.github.ojh102.chatproject.intro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.ojh102.chatproject.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_intro, SignInFragment.newInstance())
                .commit();
    }
}
