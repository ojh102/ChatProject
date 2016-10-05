package com.github.ojh102.chatproject.intro.signin.di;

import com.github.ojh102.chatproject.common.di.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

@Module
public class SignInModule {

    SignInPresenter.View view;

    public SignInModule(SignInPresenter.View view) {
        this.view = view;
    }

    @Provides
    @PerFragment
    public SignInPresenter provideSignInPresenter(SignInPresenterImpl signInPresenter) {
        return signInPresenter;
    }

    @Provides
    @PerFragment
    public SignInPresenter.View provideView() {
        return view;
    }
}
