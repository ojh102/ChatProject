package com.github.ojh102.chatproject.api;

import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.main.friend.Friend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public interface ChatApi {
    //회원가입
    @FormUrlEncoded
    @POST("intro/signup")
    Call<ServerResponse> signUp(
            @Field("id") String id,
            @Field("passwd") String passwd,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("intro/signin")
    Call<ServerResponse> signIn(
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

    //친구
    @GET("friend/list")
    Call<List<Friend>> getFriendList(@Query("id") String id);

    @GET("friend/search")
    Call<List<Friend>> getSearchList(
            @Query("myId") String myId,
            @Query("friendId") String friendId
    );

    @FormUrlEncoded
    @POST("friend/add")
    Call<ServerResponse> addFriend(
            @Field("myId") String myId,
            @Field("friendId") String friendId
    );
}
