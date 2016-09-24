package com.github.ojh102.chatproject.main.friend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.util.DividerItemDecoration;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendSearchActivity extends AppCompatActivity {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    FriendAdapter mFriendAdapter;

    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mFriendAdapter = new FriendAdapter();

        rvFriend.setLayoutManager(new LinearLayoutManager(this));
        rvFriend.addItemDecoration(new DividerItemDecoration(MyApplication.getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter(mFriendAdapter);

        mFriendAdapter.setOnClickFriendAdapterListener(new FriendAdapter.OnClickFriendAdapterListener() {
            @Override
            public void onClickFriendView(Friend friend) {
                Log.d("ojh","fv click");
                showAddFriendDialog(friend);
            }

            @Override
            public void onClickFriendId(Friend friend) {
                Log.d("ojh","fi click");
                showAddFriendDialog(friend);

            }

            @Override
            public void onClickFriendTumbnail(Friend friend) {
                Log.d("ojh","ft click");
                showAddFriendDialog(friend);
            }
        });

        mToolbar.inflateMenu(R.menu.search);
        mSearchView = (SearchView) mToolbar.getMenu().findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchFriends(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void getSearchFriends(String query) {
        ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
        Call<List<Friend>> call = chatApi.getSearchList(PropertyManager.getInstance().getId(), query);
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {

                if (response.isSuccessful()) {
                    mFriendAdapter.add(response.body());
                } else {
                    Toast.makeText(getApplicationContext(), "network fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                Log.d("ojh", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showAddFriendDialog(final Friend friend) {
        AlertDialog dialog = new AlertDialog.Builder(FriendSearchActivity.this, R.style.AppCompatAlertDialogStyle)
                .setTitle("친구 추가")
                .setMessage(friend.getId() + "님을 친구로 추가하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
                        Call<ServerResponse> call = chatApi.addFriend(PropertyManager.getInstance().getId(), friend.getId());
                        call.enqueue(new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                if(response.body().getSuccess() == 1) {
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("취소", null).create();

        dialog.show();
    }
}
