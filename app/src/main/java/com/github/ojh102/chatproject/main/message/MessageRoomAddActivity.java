package com.github.ojh102.chatproject.main.message;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.MessageApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.data.User;
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

public class MessageRoomAddActivity extends AppCompatActivity {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FriendAdapter mFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_room_add);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("채팅방 생성");

        mFriendAdapter = new FriendAdapter();
        mFriendAdapter.setOnClickFriendAdapterListener(new FriendAdapter.OnClickFriendAdapterListener() {
            @Override
            public void onClickFriendView(final User user) {

                AlertDialog dialog = new AlertDialog.Builder(MessageRoomAddActivity.this, R.style.AppCompatAlertDialogStyle)
                        .setTitle("채팅방 생성")
                        .setMessage(user.getName()+"("+user.getId() +")님과의 채팅을 시작하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
                                Call<ServerResponse> call = messageApi.createMessageRoom(PropertyManager.getInstance().getId(), user.getId());
                                call.enqueue(new Callback<ServerResponse>() {
                                    @Override
                                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("취소", null).create();

                dialog.show();
            }
        });
        rvFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriend.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter(mFriendAdapter);
        getData();
    }

    private void getData() {
        MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
        Call<List<User>> call = messageApi.getValidMessageRooms(PropertyManager.getInstance().getId());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
