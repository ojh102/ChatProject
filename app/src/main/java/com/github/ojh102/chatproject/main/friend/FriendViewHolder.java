package com.github.ojh102.chatproject.main.friend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ojh102.chatproject.common.MyApp;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class FriendViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvId)
    TextView tvId;

    @BindView(R.id.ivTumbnail)
    ImageView ivTumbnail;

    User mUser;

    public FriendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickFrinedListener != null) {
                    onClickFrinedListener.onCLickFriendView(getAdapterPosition());
                }
            }
        });
    }

    public void setFriend(User user) {
        mUser = user;
        tvId.setText(mUser.getName()+"("+ mUser.getId()+")");
        Glide.with(MyApp.getContext())
                .load(R.drawable.ic_person_black_48dp)
                .bitmapTransform(new CropCircleTransformation(MyApp.getContext()))
                .centerCrop()
                .into(ivTumbnail);

    }

    public interface OnClickFrinedListener {
        public void onCLickFriendView(int position);
    }

    OnClickFrinedListener onClickFrinedListener;

    public void setOnClickFrinedListener(OnClickFrinedListener onClickFrinedListener) {
        this.onClickFrinedListener = onClickFrinedListener;
    }

}
