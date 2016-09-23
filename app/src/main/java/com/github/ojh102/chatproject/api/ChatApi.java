package com.github.ojh102.chatproject.api;

import com.github.ojh102.chatproject.data.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public interface ChatApi {

    @FormUrlEncoded
    @POST("intro/signup")
    Call<ServerResponse> signUp(
            @Field("id") String id,
            @Field("passwd") String passwd,
            @Field("token") String token
    );

    @FormUrlEncoded
    @PUT("intro/registertoken")
    Call<ServerResponse> registerToken(
            @Field("id") String id,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("intro/signin")
    Call<ServerResponse> signIn(
            @Field("id") String id,
            @Field("passwd") String passwd,
            @Field("token") String token
    );
}
