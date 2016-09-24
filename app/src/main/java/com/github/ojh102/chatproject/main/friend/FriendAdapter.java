package com.github.ojh102.chatproject.main.friend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Friend> items = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FriendViewHolder)holder).setFriend(items.get(position));
        ((FriendViewHolder)holder).setOnClickFrinedListener(new FriendViewHolder.OnClickFrinedListener() {
            @Override
            public void onCLickFriendView(int position) {
                if(mListener != null) {
                    mListener.onClickFriendView(items.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(List<Friend> friends) {
        items = friends;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }


    public void getDummyData() {
        for(int i=0; i<10; i++) {
            Friend item = new Friend();
            item.setId("test"+i);
            item.setThumbnail("http://pncg.co.kr/upload/menu/11364366473.png");
            items.add(item);
        }
        notifyDataSetChanged();
    }

    public interface OnClickFriendAdapterListener {
        public void onClickFriendView(Friend friend);
    }

    OnClickFriendAdapterListener mListener;

    public void setOnClickFriendAdapterListener(OnClickFriendAdapterListener listener) {
        mListener = listener;
    }
}
