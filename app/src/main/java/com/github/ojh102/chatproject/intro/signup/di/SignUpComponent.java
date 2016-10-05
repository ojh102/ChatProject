package com.github.ojh102.chatproject.intro.signup.di;

import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.common.di.PerFragment;
import com.github.ojh102.chatproject.intro.signup.SignUpFragment;

import dagger.Component;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

@PerFragment
@Component(dependencies = NetworkComponent.class, modules = SignUpModule.class)
public interface SignUpComponent {
    void inject(SignUpFragment signUpFragment);
}
