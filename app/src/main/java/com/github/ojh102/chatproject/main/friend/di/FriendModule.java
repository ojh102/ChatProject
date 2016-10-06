package com.github.ojh102.chatproject.main.friend.di;

import com.github.ojh102.chatproject.common.di.PerFragment;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapter;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataModel;
import com.github.ojh102.chatproject.main.friend.adapter.FriendAdapterDataView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-10-06.
 */
@Module
public class FriendModule {
    private FriendPresenter.View friendView;
    private FriendAddPresenter.View friendAddView;
    private FriendAdapter friendAdapter;

    public FriendModule(FriendPresenter.View view, FriendAdapter friendAdapter) {
        this.friendView = view;
        this.friendAdapter = friendAdapter;
    }

    public FriendModule(FriendAddPresenter.View view, FriendAdapter friendAdapter) {
        this.friendAddView = view;
        this.friendAdapter = friendAdapter;
    }

    @Provides
    @PerFragment
    public FriendAdapterDataModel provideFriendAdapterDataModel() {
        return friendAdapter;
    }

    @Provides
    @PerFragment
    public FriendAdapterDataView provideFriendAdapterDataView() {
        return friendAdapter;
    }

    @Provides
    @PerFragment
    public FriendPresenter provideFriendPresenter(FriendPresenterImpl friendPresenter) {
        return friendPresenter;
    }

    @Provides
    @PerFragment
    public FriendPresenter.View provideFriendView() {
        return friendView;
    }

    @Provides
    @PerFragment
    public FriendAddPresenter provideFriendAddPresenter(FriendAddPresenterImpl friendAddPresenter) {
        return friendAddPresenter;
    }

    @Provides
    @PerFragment
    public FriendAddPresenter.View provideFriendAddView() {
        return friendAddView;
    }

}
