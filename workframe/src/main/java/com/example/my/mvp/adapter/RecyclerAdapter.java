package com.example.my.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by olq on 2016/5/31.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecycleHolder>{

    private Context mContext;
    private List<T> mData;
    private LayoutInflater mInflater;

    public abstract int getLayout();


    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public RecyclerAdapter(Context context,List<T> data) {
        mContext=context;
        mData=data;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleHolder(mInflater.inflate(getLayout(),parent,false));
    }

    @Override
    public void onBindViewHolder(final RecycleHolder holder, final int position) {
        if(onItemClickListener!=null){
//            holder.itemView.setBackgroundResource(R.drawable.recycle_bg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClickListener(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
        if(onItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.OnItemLongClickListener(holder.itemView,holder.getLayoutPosition());
                    return true;
                }
            });
        }
        convert(holder,mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    /**自定义RecyclerView item的点击事件的点击事件*/
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        void OnItemLongClickListener(View view, int position);
    }



    public abstract void convert(RecycleHolder holder, T data, int position);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void addData(T t){
        mData.add(t);
        notifyDataSetChanged();
    }

    public void remove(int position){
        mData.remove(position);
        notifyDataSetChanged();
    }




}
