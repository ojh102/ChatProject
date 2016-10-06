package com.github.ojh102.chatproject.main.friend;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.BaseActivity;
import com.github.ojh102.chatproject.common.MyApp;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapter;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataView;
import com.github.ojh102.chatproject.main.friend.di.DaggerFriendComponent;
import com.github.ojh102.chatproject.main.friend.di.FriendAddPresenter;
import com.github.ojh102.chatproject.main.friend.di.FriendModule;
import com.github.ojh102.chatproject.model.User;
import com.github.ojh102.chatproject.util.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendAddActivity extends BaseActivity implements FriendAddPresenter.View {

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    FriendAdapterDataView friendAdapterDataView;

    @Inject
    FriendAddPresenter friendAddPresenter;

    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void setupComponent(NetworkComponent networkComponent) {
        DaggerFriendComponent.builder()
                .networkComponent(networkComponent)
                .friendModule(new FriendModule(this, new FriendAdapter()))
                .build()
                .inject(this);
    }

    private void initView() {

        rvFriend.setLayoutManager(new LinearLayoutManager(this));
        rvFriend.addItemDecoration(new DividerItemDecoration(MyApp.getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFriend.setAdapter((FriendAdapter)friendAdapterDataView);

        ((FriendAdapter)friendAdapterDataView).setOnClickFriendAdapterListener(new FriendAdapter.OnClickFriendAdapterListener() {
            @Override
            public void onClickFriendView(User user) {
                friendAddPresenter.showAddFriendDialog(user);
            }
        });

        mToolbar.setTitle("친구의 아이디를 검색해주세요");
        mToolbar.inflateMenu(R.menu.search);
        mSearchView = (SearchView) mToolbar.getMenu().findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                friendAddPresenter.getSearchFriends(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void refresh() {
        friendAdapterDataView.refreshView();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
