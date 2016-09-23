package com.github.ojh102.chatproject.intro;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.github.ojh102.chatproject.MainActivity;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler(Looper.getMainLooper());

        checkLogin();

    }

    private void checkLogin() {
        final String id = PropertyManager.getInstance().getId();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //저장된 아이디가 있을 시 자동로그인
                if (!TextUtils.isEmpty(id)) {
                    //아이디에 토큰 put
                    ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
                    Call<ServerResponse> call = chatApi.registerToken(id, FirebaseInstanceId.getInstance().getToken());
                    call.enqueue(new Callback<ServerResponse>() {

                        Intent intent;

                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            if (response.body().getSuccess() == 1) {
                                intent = new Intent(SplashActivity.this, MainActivity.class);
                            } else {
                                intent = new Intent(SplashActivity.this, IntroActivity.class);
                            }
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            intent = new Intent(SplashActivity.this, IntroActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                } else {
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
