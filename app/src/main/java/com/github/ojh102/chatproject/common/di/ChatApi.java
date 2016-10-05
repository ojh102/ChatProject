package com.github.ojh102.chatproject.common.di;

import com.github.ojh102.chatproject.data.MessageResponse;
import com.github.ojh102.chatproject.data.MessageRoom;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by OhJaeHwan on 2016-09-23.
 */

public class ChatApi {

    private ChatApi.Api api;

    public ChatApi(Retrofit retrofit) {
        api = retrofit.create(Api.class);
    }

    //회원가입
    public Call<ServerResponse> signUp(String id, String name, String passwd, String token) {
        return api.signUp(id, name, passwd, token);
    }

    public Call<ServerResponse> signIn(String id, String passwd, String token) {
        return api.signIn(id, passwd, token);
    }

    public Call<ServerResponse> registerToken(String id, String token) {
        return api.registerToken(id, token);
    }

    //친구
    public Call<List<User>> getFriends(String id) {
        return api.getFriends(id);
    }

    public Call<List<User>> getSearchFriends(String myId, String friendId) {
        return api.getSearchFriends(myId, friendId);
    }

    public Call<ServerResponse> addFriend(String myId, String friendId) {
        return api.addFriend(myId, friendId);
    }

    //채팅방
    public Call<ServerResponse> createMessageRoom(String myId, String friendId) {
        return api.createMessageRoom(myId, friendId);
    }

    public Call<List<MessageRoom>> getMessageRooms(String myId) {
        return api.getMessageRooms(myId);
    }

    public Call<List<User>> getValidMessageRooms(String id) {
        return api.getValidMessageRooms(id);
    }

    //채팅
    public Call<MessageResponse> getMessageList(String messageId, String myId) {
        return api.getMessageList(messageId, myId);
    }

    public Call<ServerResponse> sendMessage(String messageId, String fromId, String message) {
        return api.sendMessage(messageId, fromId, message);
    }

    //api
    interface Api {
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
}

