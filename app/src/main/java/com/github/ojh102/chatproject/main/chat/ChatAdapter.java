package com.github.ojh102.chatproject.main.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.ChatRoom;
import com.github.ojh102.chatproject.data.Friend;
import com.github.ojh102.chatproject.main.friend.FriendViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ChatRoom> items = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_room, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChatViewHolder)holder).setChatRoom(items.get(position));
        ((ChatViewHolder)holder).setOnClickFrinedListener(new ChatViewHolder.OnClickChatListener() {
            @Override
            public void onCLickFriendView(int position) {
                if(mListener != null) {
                    mListener.onClickChatView(items.get(position));
                }

            }

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(List<ChatRoom> chatRooms) {
        items = chatRooms;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void getDummyData() {
        for(int i=0; i<10; i++) {
            ChatRoom item = new ChatRoom();
            Friend friend = new Friend();
            friend.setName("test"+i);
            friend.setThumbnail("http://www.freeiconspng.com/uploads/msn-people-person-profile-user-icon--icon-search-engine-11.png");
            item.setFriend(friend);
            item.setLastMessage("message"+i);
            item.setLastDate("2016-01-01 00:0"+i);
            items.add(item);
        }
        notifyDataSetChanged();
    }

    public interface OnClickChaTAdapterListener {
        public void onClickChatView(ChatRoom chatRoom);
    }

    OnClickChaTAdapterListener mListener;

    public void setOnClickFriendAdapterListener(OnClickChaTAdapterListener listener) {
        mListener = listener;
    }
}
