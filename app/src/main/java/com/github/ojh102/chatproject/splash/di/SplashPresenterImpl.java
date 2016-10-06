package com.github.ojh102.chatproject.splash.di;

import android.os.Handler;
import android.text.TextUtils;

import com.github.ojh102.chatproject.common.di.ChatApi;
import com.github.ojh102.chatproject.model.ServerResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by OhJaeHwan on 2016-09-29.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashPresenter.View view;
    private Handler handler;
    private ChatApi chatApi;

    @Inject
    public SplashPresenterImpl(SplashPresenter.View view, @Named("main_thread") Handler handler, ChatApi chatApi) {
        this.view = view;
        this.handler = handler;
        this.chatApi = chatApi;
    }

    @Override
    public void checkLogin(String id) {
        handler.postDelayed(() -> {
            if (!TextUtils.isEmpty(id)) {
                chatApi.registerToken(id, FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<ServerResponse>() {

                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        view.showToast(response.body().getMessage());

                        if (response.body().getSuccess() == 1) {
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
            } else {
                view.navigateToIntro();
            }
        }, 2000);

    }
}
