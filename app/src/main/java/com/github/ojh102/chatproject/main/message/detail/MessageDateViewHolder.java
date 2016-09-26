package com.github.ojh102.chatproject.main.message.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ojh102.chatproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageDateViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvDate)
    TextView tvDate;

    public MessageDateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
