package com.github.ojh102.chatproject.common;

import com.github.ojh102.chatproject.model.MessageResponse;
import com.github.ojh102.chatproject.model.MessageRoom;
import com.github.ojh102.chatproject.model.ServerResponse;
import com.github.ojh102.chatproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

//api
public interface MessageApi {
    //회원가입
    @FormUrlEncoded
    @POST("intro/signup")
    Call<ServerResponse> signUp(
            @Field("id") String id,
            @Field("name") String name,
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
    Call<List<User>> getFriends(@Query("id") String id);

    @GET("friend/search")
    Call<List<User>> getSearchFriends(
            @Query("myId") String myId,
            @Query("friendId") String friendId
    );

    @FormUrlEncoded
    @POST("friend/add")
    Call<ServerResponse> addFriend(
            @Field("myId") String myId,
            @Field("friendId") String friendId
    );

    //채팅방
    @FormUrlEncoded
    @POST("chat/room")
    Call<ServerResponse> createMessageRoom(
            @Field("myId") String myId,
            @Field("friendId") String friendId
    );

    @GET("chat/room")
    Call<List<MessageRoom>> getMessageRooms(@Query("myId") String myId);

    @GET("chat/search")
    Call<List<User>> getValidMessageRooms(@Query("myId") String id);

    //채팅
    @GET("chat/list")
    Call<MessageResponse> getMessageList(@Query("messageId") String messageId,
                                         @Query("myId") String myId);

    @FormUrlEncoded
    @POST("chat/send")
    Call<ServerResponse> sendMessage(@Field("messageId") String messageId,
                                     @Field("fromId") String fromId,
                                     @Field("message") String message);

}

