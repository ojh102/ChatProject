package com.github.ojh102.chatproject.intro.signin.di;

import android.text.TextUtils;

import com.github.ojh102.chatproject.common.di.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.PropertyManager;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

public class SignInPresenterImpl implements SignInPresenter {

    private SignInPresenter.View view;
    private ChatApi chatApi;

    @Inject
    public SignInPresenterImpl(View view, ChatApi chatApi) {
        this.view = view;
        this.chatApi = chatApi;
    }


    @Override
    public void signIn(String id, String password, String token) {

        if (TextUtils.isEmpty(id)) {
            view.showToast("id를 입력해 주세요");
        } else if (TextUtils.isEmpty(password)) {
            view.showToast("비밀번호를 입력해 주세요");
        } else {
            chatApi.signIn(id, password, token).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    view.showToast(response.body().getMessage());
                    if (response.body().getSuccess() == 1) {
                        PropertyManager.getInstance().setId(id);
                        view.navigateToMain();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    view.showToast(t.getMessage());
                }
            });
        }
    }
}
