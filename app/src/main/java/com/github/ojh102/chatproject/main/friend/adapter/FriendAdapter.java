package com.github.ojh102.chatproject.main.friend.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.main.friend.FriendViewHolder;
import com.github.ojh102.chatproject.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FriendAdapterDataModel, FriendAdapterDataView{

    List<User> items = new ArrayList<>();

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

    @Override
    public void setList(List<User> users) {
        items = users;
    }

    @Override
    public void refreshView() {
        notifyDataSetChanged();
    }


    public interface OnClickFriendAdapterListener {
        public void onClickFriendView(User user);
    }

    OnClickFriendAdapterListener mListener;

    public void setOnClickFriendAdapterListener(OnClickFriendAdapterListener listener) {
        mListener = listener;
    }
}
