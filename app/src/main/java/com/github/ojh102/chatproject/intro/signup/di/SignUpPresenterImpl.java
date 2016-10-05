package com.github.ojh102.chatproject.intro.signup.di;

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

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpPresenter.View view;
    private ChatApi chatApi;

    @Inject
    public SignUpPresenterImpl(View view, ChatApi chatApi) {
        this.view = view;
        this.chatApi = chatApi;
    }

    @Override
    public void signUp(String id, String name, String passwd, String confirmPasswd, String token) {
        if (TextUtils.isEmpty(id)) {
            view.showToast("id를 입력해 주세요");
        } else if (TextUtils.isEmpty(passwd)) {
            view.showToast("닉네임을 입력해 주세요");
        } else if (TextUtils.isEmpty(passwd)) {
            view.showToast("비밀번호를 입력해 주세요");
        } else if (!passwd.equals(confirmPasswd)) {
            view.showToast("비밀번호가 맞지 않습니다.");
        } else {
            chatApi.signUp(id, name, passwd, token).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    view.showToast(response.body().getMessage());
                    if (response.body().getSuccess() == 1) {

                        PropertyManager.getInstance().setId(id);
                        PropertyManager.getInstance().setName(name);

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
