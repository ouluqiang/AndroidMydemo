package com.personage.myolq.mvp.view.adapter;

import android.content.Context;
import android.view.View;

import com.example.my.mvp.adapter.listadapter.ListAdapter;
import com.example.my.mvp.adapter.listadapter.ListHolder;
import com.example.widget.sortlist.SortModel;
import com.personage.myolq.R;
import com.personage.myolq.bmob.bean.Friend;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class FriendsAdapter extends ListAdapter<Friend> {

    public List<Friend> mDatas;

    public FriendsAdapter(Context context, List<Friend> mDatas) {
        super(context, mDatas);
        this.mDatas=mDatas;
    }

    @Override
    public void convert(ListHolder holder, Friend friend) {
//根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(holder.getmPosition());

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (holder.getmPosition() == getPositionForSection(section)) {
            holder.setVisibility(R.id.catalog,View.VISIBLE);
            holder.setText(R.id.catalog,friend.getSortLetters());
        } else {
            holder.setVisibility(R.id.catalog,View.GONE);
        }
        holder.setText(R.id.title,friend.getName());
//        viewHolder.tvTitle.setText(this.list.get(position).getName());

    }

    @Override
    public int getLayout() {
        return R.layout.item_sort;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mDatas.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mDatas.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<Friend> list){
        this.mDatas = list;
        notifyDataSetChanged();
    }


}
