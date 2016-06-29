package com.personage.myolq.mvp.view.adapter;

import android.content.Context;
import android.view.View;

import com.personage.myolq.R;
import com.personage.myolq.bmob.adapter.base.BaseRecyclerAdapter;
import com.personage.myolq.bmob.adapter.base.BaseRecyclerHolder;
import com.personage.myolq.bmob.adapter.base.IMutlipleItem;
import com.personage.myolq.bmob.bean.Conversation;
import com.personage.myolq.bmob.util.TimeUtil;

import java.util.Collection;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class RecyclerConversation extends BaseRecyclerAdapter<Conversation> {
    /**
     * 支持一种或多种Item布局
     *
     * @param context
     * @param items
     * @param datas
     */
    public RecyclerConversation(Context context, IMutlipleItem<Conversation> items, Collection<Conversation> datas) {
        super(context, items, datas);
    }


//    public RecyclerConversation(Context context, List<Conversation> data) {
//        super(context, data);
//    }
//
//    @Override
//    public int getLayout() {
//        return R.layout.item_conversation;
//    }
//
//    @Override
//    public void convert(RecycleHolder holder, Conversation data, int position) {
//        holder.setText(R.id.tv_name,data.getcName());
//        holder.setText(R.id.tv_context,data.getLastMessageContent());
//        holder.setText(R.id.tv_data, TimeUtil.getChatTime(false,data.getLastMessageTime()));
//    }

    @Override
    public void bindView(BaseRecyclerHolder holder, Conversation item, int position) {
        holder.setText(R.id.tv_recent_msg,item.getLastMessageContent());
        holder.setText(R.id.tv_recent_time,TimeUtil.getChatTime(false,item.getLastMessageTime()));
        //会话图标
        Object obj = item.getAvatar();
        if(obj instanceof String){
            String avatar=(String)obj;
            holder.setImageView(avatar, R.mipmap.head, R.id.iv_recent_avatar);
        }else{
            int defaultRes = (int)obj;
            holder.setImageView(null, defaultRes, R.id.iv_recent_avatar);
        }
        //会话标题
        holder.setText(R.id.tv_recent_name, item.getcName());
        //查询指定未读消息数
        long unread = item.getUnReadCount();
        if(unread>0){
            holder.setVisible(R.id.tv_recent_unread, View.VISIBLE);
            holder.setText(R.id.tv_recent_unread, String.valueOf(unread));
        }else{
            holder.setVisible(R.id.tv_recent_unread, View.GONE);
        }
    }
}
