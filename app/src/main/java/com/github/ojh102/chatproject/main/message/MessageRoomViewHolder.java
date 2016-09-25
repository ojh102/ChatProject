package com.github.ojh102.chatproject.main.message;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.MessageRoom;
import com.github.ojh102.chatproject.data.User;
import com.github.ojh102.chatproject.util.PropertyManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class MessageRoomViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvLastMessage)
    TextView tvLastMessage;

    @BindView(R.id.tvLastDate)
    TextView tvLastDate;

    @BindView(R.id.ivTumbnail)
    ImageView ivTumbnail;

    MessageRoom mMessageRoom;

    public MessageRoomViewHolder(View itemView) {
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

    public void setChatRoom(MessageRoom messageRoom) {
        mMessageRoom = messageRoom;
        User friend = null;
        for(User user : mMessageRoom.getUser()) {
            if(!PropertyManager.getInstance().getId().equals(user.getId())) {
                friend = user;
            }
        }

        tvName.setText(friend.getName()+"("+friend.getId()+")");
        if(mMessageRoom.getLastMessage() != null) {
            tvLastMessage.setText(mMessageRoom.getLastMessage());
        }
        if(mMessageRoom.getLastDate() != null) {
            tvLastDate.setText(mMessageRoom.getLastDate());
        }
        Glide.with(MyApplication.getContext())
                .load(R.drawable.ic_person_black_48dp)
                .bitmapTransform(new CropCircleTransformation(MyApplication.getContext()))
                .centerCrop()
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
