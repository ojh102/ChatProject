package com.github.ojh102.chatproject.main.message.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.ojh102.chatproject.common.MyApp;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.MessageData;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter {

    List<MessageData> items = new ArrayList<>();

    private static final int VIEW_TYPE_RECEIVE = 300;
    private static final int VIEW_TYPE_DATE = 200;
    private static final int VIEW_TYPE_SEND = 100;

    @Override
    public int getItemViewType(int position) {
        MessageData item = items.get(position);
        switch (item.getType()){
            case MessageData.TYPE_DATE:
                return VIEW_TYPE_DATE;
            case MessageData.TYPE_SEND:
                return VIEW_TYPE_SEND;
            case MessageData.TYPE_RECEIVE:
                return VIEW_TYPE_RECEIVE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        switch (viewType){
            case VIEW_TYPE_DATE:
                view = inflater.inflate(R.layout.view_item_date, parent, false);
               return new MessageDateViewHolder(view);
            case VIEW_TYPE_RECEIVE:
                view = inflater.inflate(R.layout.view_item_recieve, parent, false);
                return new MessageReceiverViewHolder(view);
            case VIEW_TYPE_SEND:
                view = inflater.inflate(R.layout.view_item_send, parent, false);
                return new MessageSendViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case VIEW_TYPE_DATE :
                ((MessageDateViewHolder) holder).tvDate.setText(items.get(position).getDate());
                break;
            case VIEW_TYPE_SEND:
                ((MessageSendViewHolder)holder).tvMessage.setText(items.get(position).getMessage());
                ((MessageSendViewHolder)holder).tvTime.setText(items.get(position).getTime());
                break;
            case VIEW_TYPE_RECEIVE:
                Glide.with(MyApp.getContext())
                        .load(R.drawable.ic_person_black_48dp)
                        .centerCrop()
                        .into(((MessageReceiverViewHolder)holder).ivTumbnail);

                ((MessageReceiverViewHolder)holder).tvName.setText(items.get(position).getName());
                ((MessageReceiverViewHolder)holder).tvMessage.setText(items.get(position).getMessage());
                ((MessageReceiverViewHolder)holder).tvTime.setText(items.get(position).getTime());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(MessageData item) {

        if(items.size() == 0) {
            MessageData dateData = new MessageData();
            dateData.setDate(item.getDate());
            dateData.setType(MessageData.TYPE_DATE);
            item.setType(MessageData.TYPE_DATE);
            items.add(dateData);

        } else if(item != null && items.size() > 1 && item.getDate().compareTo(items.get(items.size()-2).getDate()) != 0 ) {
            MessageData dateData = new MessageData();
            dateData.setDate(item.getDate());
            dateData.setType(MessageData.TYPE_DATE);
            item.setType(MessageData.TYPE_DATE);
            items.add(dateData);
        }

        if(item.getFromId().equals(PropertyManager.getInstance().getId())) {
            item.setType(MessageData.TYPE_SEND);
        } else {
            item.setType(MessageData.TYPE_RECEIVE);
        }
        items.add(item);
        notifyItemChanged(items.size()-1);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }
}
