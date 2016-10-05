package com.github.ojh102.chatproject.intro.signin.di;

import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.common.di.PerFragment;
import com.github.ojh102.chatproject.intro.signin.SignInFragment;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */
@PerFragment
@Component(dependencies = NetworkComponent.class, modules = SignInModule.class)
public interface SignInComponent {
    void inject(SignInFragment signInFragment);
}
