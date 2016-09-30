package com.github.ojh102.chatproject.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.dagger.BackgroundModule;
import com.github.ojh102.chatproject.common.dagger.NetworkModule;
import com.github.ojh102.chatproject.intro.dagger.DaggerSplashComponent;
import com.github.ojh102.chatproject.intro.dagger.SplashModule;
import com.github.ojh102.chatproject.intro.dagger.SplashPresenter;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.util.PropertyManager;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.View{

    @Inject
    SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        DaggerSplashComponent.builder()
                .backgroundModule(new BackgroundModule())
                .networkModule(new NetworkModule())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        checkLogin();
    }

    private void checkLogin() {
        final String id = PropertyManager.getInstance().getId();
        splashPresenter.checkLogin(id);
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToIntro() {
        startActivity(new Intent(SplashActivity.this, IntroActivity.class));
        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
