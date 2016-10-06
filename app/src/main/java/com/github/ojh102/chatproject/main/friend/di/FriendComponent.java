package com.github.ojh102.chatproject.main.friend.di;

import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.common.di.PerFragment;
import com.github.ojh102.chatproject.main.friend.FriendAddActivity;
import com.github.ojh102.chatproject.main.friend.FriendFragment;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-10-06.
 */
@PerFragment
@Component(dependencies = NetworkComponent.class, modules = FriendModule.class)
public interface FriendComponent {
    void inject(FriendFragment friendFragment);
    void inject(FriendAddActivity friendAddActivity);
}
