package com.personage.myolq.mvp.view.adapter;

import android.content.Context;

import com.example.my.mvp.adapter.recycleadapter.RecycleHolder;
import com.example.my.mvp.adapter.recycleadapter.RecyclerAdapter;
import com.personage.myolq.R;
import com.personage.myolq.bmob.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class AdditionAdapter extends RecyclerAdapter<User>{


    public AdditionAdapter(Context context, List<User> data) {
        super(context, data);
    }


    @Override
    public int getLayout() {
        return R.layout.item_friend;
    }

    @Override
    public void convert(RecycleHolder holder, User data, int position) {
        holder.setText(R.id.item_name,data.getNickname());
    }



}
