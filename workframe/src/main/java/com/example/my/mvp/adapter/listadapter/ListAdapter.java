package com.example.my.mvp.adapter.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public abstract class ListAdapter <T> extends BaseAdapter
{

    //这些属性都是每个适配器中都能用到的，访问控制符设置为protected，以便继承的子类都能访问
    protected LayoutInflater mInflater;
    protected List<T> mDatas;//数据源
    protected Context mContext;
//    protected int layoutId;//item布局文件

    public ListAdapter(Context context, List<T> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mDatas.size()!=0?mDatas.size():0;
    }

    @Override
    public T getItem(int position) {//这里的返回值类型是T，不是自动生成的Object
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //初始化ViewHolder，加载我们的item布局文件
        ListHolder holder = ListHolder.get(mContext, convertView, parent,getLayout() , position);
        convert(holder, getItem(position));//getItem(position)的类型就是T，这句话在子类中的具体实现就是给具体的控件初始化
        //并赋值，初始化赋值控件时需要viewHolder和具体的数据Java bean，在这里抽象出来就是类型T
        return holder.getConvertView();//返回convertView
    }
    public abstract void convert(ListHolder holder, T t);

    public abstract int getLayout();

    public void addlist(List<T> t){
        mDatas.addAll(t);
        notifyDataSetChanged();
    }

}
