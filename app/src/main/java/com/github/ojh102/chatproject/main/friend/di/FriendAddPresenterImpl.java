package com.github.ojh102.chatproject.main.friend.di;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.di.ChatApi;
import com.github.ojh102.chatproject.main.friend.FriendAddActivity;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataModel;
import com.github.ojh102.chatproject.model.ServerResponse;
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

public class FriendAddPresenterImpl implements FriendAddPresenter {

    private FriendAddPresenter.View view;
    private ChatApi chatApi;
    private FriendAdapterDataModel friendAdapterDataModel;

    @Inject
    public FriendAddPresenterImpl(View view, ChatApi chatApi, FriendAdapterDataModel friendAdapterDataModel) {
        this.view = view;
        this.chatApi = chatApi;
        this.friendAdapterDataModel = friendAdapterDataModel;
    }

    @Override
    public void getSearchFriends(String query) {
        chatApi.getSearchFriends(PropertyManager.getInstance().getId(), query).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    friendAdapterDataModel.setList(response.body());
                    view.refresh();
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

    @Override
    public void showAddFriendDialog(User user) {
        AlertDialog dialog = new AlertDialog.Builder((FriendAddActivity)view, R.style.AppCompatAlertDialogStyle)
                .setTitle("친구 추가")
                .setMessage(user.getName()+"("+user.getId() +")님을 친구로 추가하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       chatApi.addFriend(PropertyManager.getInstance().getId(), user.getId()).enqueue(new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                view.showToast(response.body().getMessage());
                                view.finish();
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                view.showToast(t.getMessage());
                            }
                        });
                    }
                })
                .setNegativeButton("취소", null).create();

        dialog.show();
    }

}
