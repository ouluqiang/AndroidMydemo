package com.example.my.mvp.adapter.recycleadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by olq on 2016/5/27.
 */
public class RecycleHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public RecycleHolder(View view) {
        super(view);
        mViews=new SparseArray<>();
    }

    public <T extends View> T findViewById(int id){
        View view=mViews.get(id);
        if(view==null){
            view=itemView.findViewById(id);
            mViews.put(id,view);
        }
        return (T) view;
    }

    public RecycleHolder setText(int id,CharSequence text){
        TextView textView=findViewById(id);
        textView.setText(text);
        return this;
    }




}
