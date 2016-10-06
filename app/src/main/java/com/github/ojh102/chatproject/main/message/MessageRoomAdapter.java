package com.github.ojh102.chatproject.main.message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.model.MessageRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class MessageRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<MessageRoom> items = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_room, parent, false);
        return new MessageRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageRoomViewHolder)holder).setChatRoom(items.get(position));
        ((MessageRoomViewHolder)holder).setOnClickFrinedListener(new MessageRoomViewHolder.OnClickChatListener() {
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

    public void add(List<MessageRoom> messageRooms) {
        items = messageRooms;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public interface OnClickChaTAdapterListener {
        public void onClickChatView(MessageRoom messageRoom);
    }

    OnClickChaTAdapterListener mListener;

    public void setOnClickFriendAdapterListener(OnClickChaTAdapterListener listener) {
        mListener = listener;
    }
}
