package com.github.ojh102.chatproject.main.message.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ojh102.chatproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lady on 2016. 9. 10..
 */
public class MessageSendViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMessage)
    TextView tvMessage;

    public MessageSendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
