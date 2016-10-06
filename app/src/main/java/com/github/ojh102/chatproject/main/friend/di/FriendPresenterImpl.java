package com.github.ojh102.chatproject.main.friend.di;

import com.github.ojh102.chatproject.common.di.ChatApi;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataModel;
import com.github.ojh102.chatproject.model.User;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by OhJaeHwan on 2016-10-06.
 */

public class FriendPresenterImpl implements FriendPresenter {

    private FriendPresenter.View view;
    private ChatApi chatApi;
    private FriendAdapterDataModel friendAdapterDataModel;

    @Inject
    public FriendPresenterImpl(View view, ChatApi chatApi, FriendAdapterDataModel friendAdapterDataModel) {
        this.view = view;
        this.chatApi = chatApi;
        this.friendAdapterDataModel = friendAdapterDataModel;
    }

    @Override
    public void getData() {
        chatApi.getFriends(PropertyManager.getInstance().getId()).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null) {
                        friendAdapterDataModel.setList(response.body());
                        view.refresh();
                    }
                } else {
                    view.showToast("network fail");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view.showToast(t.getMessage());
            }
        });
    }
}
