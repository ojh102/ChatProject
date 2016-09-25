package com.github.ojh102.chatproject.intro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.util.BackPressCloseHandler;

public class IntroActivity extends AppCompatActivity {

    BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_intro, SignInFragment.newInstance())
                .commit();

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            backPressCloseHandler.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
