package com.github.ojh102.chatproject.main.friend;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ojh102.chatproject.common.MyApp;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.MessageApi;
import com.github.ojh102.chatproject.data.User;
import com.github.ojh102.chatproject.util.DividerItemDecoration;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendFragment extends Fragment {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    FriendAdapter mFriendAdapter;

    public static FriendFragment newInstance() {
        FriendFragment fragment = new FriendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);

        mFriendAdapter = new FriendAdapter();
        rvFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriend.addItemDecoration(new DividerItemDecoration(MyApp.getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter(mFriendAdapter);
        getData();

        return view;
    }

    public void getData() {
        MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
        Call<List<User>> call = messageApi.getFriends(PropertyManager.getInstance().getId());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null)
                        mFriendAdapter.add(response.body());
                } else {
                    Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnAddFriend)
    public void onClickAddFriend() {
        Intent intent = new Intent(getContext(), FriendAddActivity.class);
        startActivity(intent);
    }


}
