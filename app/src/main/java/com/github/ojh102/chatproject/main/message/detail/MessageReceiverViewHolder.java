package com.github.ojh102.chatproject.main.message.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ojh102.chatproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageReceiverViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivTumbnail)
    ImageView ivTumbnail;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public MessageReceiverViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
