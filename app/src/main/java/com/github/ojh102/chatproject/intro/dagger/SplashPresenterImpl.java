package com.github.ojh102.chatproject.intro.dagger;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.github.ojh102.chatproject.common.MessageApi;
import com.github.ojh102.chatproject.common.dagger.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private View view;
    private ChatApi chatApi;
    private Handler handler;

    @Inject
    public SplashPresenterImpl(View view, ChatApi chatApi, Handler handler) {
        this.view = view;
        this.chatApi = chatApi;
        this.handler = handler;
    }

    @Override
    public void checkLogin(String id) {
        handler.postDelayed(() -> {
            if (!TextUtils.isEmpty(id)) {
                chatApi.registerToken(id, FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<ServerResponse>() {

                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        view.showToast(response.body().getMessage());

                        if(response.body().getSuccess() == 1) {
                            view.navigateToMain();
                        } else {
                            view.navigateToIntro();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        view.navigateToIntro();
                    }
                });
            }
        }, 2000);

    }
}
