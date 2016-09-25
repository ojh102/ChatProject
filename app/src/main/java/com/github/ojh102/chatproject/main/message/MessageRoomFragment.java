package com.github.ojh102.chatproject.main.message;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.MessageApi;
import com.github.ojh102.chatproject.data.MessageRoom;
import com.github.ojh102.chatproject.main.message.detail.MessageActivity;
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

import static com.github.ojh102.chatproject.main.message.detail.MessageActivity.KEY_MESSAGE;
public class MessageRoomFragment extends Fragment {

    @BindView(R.id.rvMessage)
    RecyclerView mRecyclerView;

    MessageRoomAdapter messageRoomAdapter;

    public static MessageRoomFragment newInstance() {
        MessageRoomFragment fragment = new MessageRoomFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_room, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {
        messageRoomAdapter = new MessageRoomAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(messageRoomAdapter);
        messageRoomAdapter.setOnClickFriendAdapterListener(new MessageRoomAdapter.OnClickChaTAdapterListener() {
            @Override
            public void onClickChatView(MessageRoom messageRoom) {
                Intent intent = new Intent(getContext(), MessageActivity.class);
                intent.putExtra(KEY_MESSAGE, messageRoom.getMessageId());
                startActivity(intent);
            }
        });
        getData();
    }

    public void getData() {
        MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
        Call<List<MessageRoom>> call = messageApi.getMessageRooms(PropertyManager.getInstance().getId());
        call.enqueue(new Callback<List<MessageRoom>>() {
            @Override
            public void onResponse(Call<List<MessageRoom>> call, Response<List<MessageRoom>> response) {
                if(response.isSuccessful()) {
                    messageRoomAdapter.add(response.body());
                } else {
                    Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MessageRoom>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnAddMessageRoom)
    public void onClickAddMessageRoom() {
        Intent intent = new Intent(getContext(), MessageRoomAddActivity.class);
        startActivity(intent);
    }

}
