package com.github.ojh102.chatproject.common.dagger;

import com.github.ojh102.chatproject.common.RetrofitCreator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return RetrofitCreator.createRetrofit();
    }

    @Provides
    @Singleton
    public ChatApi provideChatApi(Retrofit retrofit) {

        return new ChatApi(retrofit);
    }
}