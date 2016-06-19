package com.personage.myolq.mvp.view.adapter;

import android.content.Context;

import com.example.javabean.entity.Conversation;
import com.example.my.mvp.adapter.RecycleHolder;
import com.example.my.mvp.adapter.RecyclerAdapter;
import com.personage.myolq.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class RecyclerConversation extends RecyclerAdapter<Conversation>{


    public RecyclerConversation(Context context, List<Conversation> data) {
        super(context, data);
    }

    @Override
    public int getLayout() {
        return R.layout.item_conversation;
    }

    @Override
    public void convert(RecycleHolder holder, Conversation data, int position) {
        holder.setText(R.id.tv_name,data.getcName());
        holder.setText(R.id.tv_context,data.getLastMessageContent());
        holder.setText(R.id.tv_data,String.valueOf(data.getLastMessageTime()));
    }
}
