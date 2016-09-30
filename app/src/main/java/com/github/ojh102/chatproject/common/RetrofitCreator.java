package com.github.ojh102.chatproject.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static final String SERVER = "http://ec2-52-78-125-221.ap-northeast-2.compute.amazonaws.com";

    public static Retrofit createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}