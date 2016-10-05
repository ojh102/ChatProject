package com.github.ojh102.chatproject.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.BaseActivity;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.intro.IntroActivity;
import com.github.ojh102.chatproject.splash.di.DaggerSplashComponent;
import com.github.ojh102.chatproject.splash.di.SplashModule;
import com.github.ojh102.chatproject.splash.di.SplashPresenter;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.util.PropertyManager;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashPresenter.View{

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkLogin();
    }

    @Override
    protected void setupComponent(NetworkComponent networkComponent) {
        DaggerSplashComponent.builder()
                .networkComponent(networkComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
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
