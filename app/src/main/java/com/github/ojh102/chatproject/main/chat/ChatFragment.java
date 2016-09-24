package com.github.ojh102.chatproject.main.chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.ChatRoom;
import com.github.ojh102.chatproject.data.Friend;
import com.github.ojh102.chatproject.intro.IntroActivity;
import com.github.ojh102.chatproject.main.friend.FriendAdapter;
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

public class ChatFragment extends Fragment {

    @BindView(R.id.rvChat)
    RecyclerView rvChat;

    ChatAdapter mChatAapter;

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);

        mChatAapter = new ChatAdapter();
        rvChat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChat.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvChat.setAdapter(mChatAapter);
//        getData();
        mChatAapter.getDummyData();

        return view;
    }

    public void getData() {
        ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
        Call<List<ChatRoom>> call = chatApi.getRoom(PropertyManager.getInstance().getId());
        call.enqueue(new Callback<List<ChatRoom>>() {
            @Override
            public void onResponse(Call<List<ChatRoom>> call, Response<List<ChatRoom>> response) {
                if(response.isSuccessful()) {
                    mChatAapter.add(response.body());
                } else {
                    Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChatRoom>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnAddChat)
    public void onClickAddChat() {
        Intent intent = new Intent(getContext(), ChatAddActivity.class);
        startActivity(intent);
    }

}
