package com.github.ojh102.chatproject.intro.signup.di;

import com.github.ojh102.chatproject.common.di.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by OhJaeHwan on 2016-10-05.
 */

@Module
public class SignUpModule {

    SignUpPresenter.View view;

    public SignUpModule(SignUpPresenter.View view) {
        this.view = view;
    }

    @Provides
    @PerFragment
    public SignUpPresenter provideSignUpPresenter(SignUpPresenterImpl signUpPresenter) {
        return signUpPresenter;
    }

    @Provides
    @PerFragment
    public SignUpPresenter.View provideView() {
        return view;
    }
}
