package com.github.ojh102.chatproject.main.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.Friend;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.main.friend.FriendAdapter;
import com.github.ojh102.chatproject.util.DividerItemDecoration;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.ojh102.chatproject.MyApplication.getContext;

/**
 * Created by OhJaeHwan on 2016-09-25.
 */

public class ChatAddActivity extends AppCompatActivity {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    FriendAdapter mFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_friend);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mFriendAdapter = new FriendAdapter();
        mFriendAdapter.setOnClickFriendAdapterListener(new FriendAdapter.OnClickFriendAdapterListener() {
            @Override
            public void onClickFriendView(Friend friend) {
//                ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
//                Call<ServerResponse> call = chatApi.createMessage(PropertyManager.getInstance().getId(), friend.getId());
//                call.enqueue(new Callback<ServerResponse>() {
//                    @Override
//                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        if (response.body().getSuccess() == 1) {
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ServerResponse> call, Throwable t) {
//                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        rvFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriend.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter(mFriendAdapter);
        getData();
    }

    private void getData() {
        ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
        Call<List<Friend>> call = chatApi.getFriendList(PropertyManager.getInstance().getId());
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        mFriendAdapter.add(response.body());
                } else {
                    Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
