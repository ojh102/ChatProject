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

import com.github.ojh102.chatproject.common.BaseFragment;
import com.github.ojh102.chatproject.common.MyApp;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.MessageApi;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapter;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataView;
import com.github.ojh102.chatproject.main.friend.di.DaggerFriendComponent;
import com.github.ojh102.chatproject.main.friend.di.FriendModule;
import com.github.ojh102.chatproject.main.friend.di.FriendPresenter;
import com.github.ojh102.chatproject.model.User;
import com.github.ojh102.chatproject.util.DividerItemDecoration;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendFragment extends BaseFragment implements FriendPresenter.View {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    @Inject
    public FriendPresenter friendPresenter;

    @Inject
    FriendAdapterDataView friendAdapterDataView;

    public static FriendFragment newInstance() {
        FriendFragment fragment = new FriendFragment();
        return fragment;
    }

    @Override
    protected void setupComponent(NetworkComponent networkComponent) {
        DaggerFriendComponent.builder()
                .networkComponent(networkComponent)
                .friendModule(new FriendModule(this, new FriendAdapter()))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);

        rvFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriend.addItemDecoration(new DividerItemDecoration(MyApp.getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter((FriendAdapter)friendAdapterDataView);

        getData();

        return view;
    }

    public void getData() {
        friendPresenter.getData();
    }

    @Override
    public void refresh() {
        friendAdapterDataView.refreshView();
    }

    @Override
    public void showToast(String messgae) {
        Toast.makeText(getContext(), messgae, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnAddFriend)
    public void onClickAddFriend() {
        Intent intent = new Intent(getContext(), FriendAddActivity.class);
        startActivity(intent);
    }


}
