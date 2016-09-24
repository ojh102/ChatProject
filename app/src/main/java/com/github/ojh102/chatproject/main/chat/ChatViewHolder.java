package com.github.ojh102.chatproject.main.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.ChatRoom;
import com.github.ojh102.chatproject.data.Friend;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvLastMessage)
    TextView tvLastMessage;

    @BindView(R.id.tvLastDate)
    TextView tvLastDate;

    @BindView(R.id.ivTumbnail)
    ImageView ivTumbnail;

    ChatRoom mChatRoom;

    public ChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickChatListener != null) {
                    onClickChatListener.onCLickFriendView(getAdapterPosition());
                }
            }
        });
    }

    public void setChatRoom(ChatRoom chatRoom) {
        mChatRoom = chatRoom;
        tvName.setText(mChatRoom.getFriend().getName());
        if(mChatRoom.getLastDate() != null) {
            tvLastMessage.setText(mChatRoom.getLastMessage());
        }
        if(mChatRoom.getLastDate() != null) {
            tvLastDate.setText(mChatRoom.getLastDate());
        }
        Glide.with(MyApplication.getContext())
                .load(mChatRoom.getFriend().getThumbnail())
                .bitmapTransform(new CropCircleTransformation(MyApplication.getContext()))
                .into(ivTumbnail);

    }

    public interface OnClickChatListener {
        public void onCLickFriendView(int position);
    }

    OnClickChatListener onClickChatListener;

    public void setOnClickFrinedListener(OnClickChatListener onClickFrinedListener) {
        this.onClickChatListener = onClickFrinedListener;
    }

}
