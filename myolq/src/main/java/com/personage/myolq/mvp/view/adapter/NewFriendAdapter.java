package com.personage.myolq.mvp.view.adapter;

import android.content.Context;
import android.view.View;

import com.example.my.mvp.adapter.recycleadapter.RecycleHolder;
import com.example.my.mvp.adapter.recycleadapter.RecyclerAdapter;
import com.example.utils.ToastUtil;
import com.example.widget.rippleclick.Click_Text;
import com.orhanobut.logger.Logger;
import com.personage.myolq.R;
import com.personage.myolq.bmob.Config;
import com.personage.myolq.bmob.bean.AgreeAddFriendMessage;
import com.personage.myolq.bmob.bean.Friend;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.bmob.db.NewFriend;
import com.personage.myolq.bmob.db.NewFriendManager;
import com.personage.myolq.bmob.model.UserModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class NewFriendAdapter extends RecyclerAdapter<NewFriend> {

    private Context context;

    public NewFriendAdapter(Context context, List<NewFriend> data) {
        super(context, data);
        this.context=context;
    }

    @Override
    public int getLayout() {
        return R.layout.item_newfriend;
    }

    @Override
    public void convert(final RecycleHolder holder, final NewFriend data, int position) {
        holder.setText(R.id.item_username,data.getName());
        holder.setText(R.id.item_nickname,data.getMsg());
        Integer status =data.getStatus();
        Logger.i("bindData:"+status+","+data.getUid()+","+data.getTime());
        final Click_Text item_newfriend_add=holder.findViewById(R.id.item_newfriend_add);
        if(status==null || status== Config.STATUS_VERIFY_NONE||status ==Config.STATUS_VERIFY_READED){//未添加/已读未添加
            item_newfriend_add.setText("接受");
            item_newfriend_add.setEnabled(true);
//            holder.setEnabled(R.id.btn_aggree,true);
            item_newfriend_add.setOnClickListener(new Click_Text.OnClickListener() {
                @Override
                public void onClick(View v) {//发送消息
                    agreeAdd(data, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            item_newfriend_add.setText("已添加");
                            item_newfriend_add.setEnabled(false);
//                            holder.setText(R.id.btn_aggree,"已添加");
//                            holder.setEnabled(R.id.btn_aggree,false);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            item_newfriend_add.setEnabled(true);
//                            holder.setEnabled(R.id.btn_aggree,true);
                            ToastUtil.show(context,"添加好友失败:" + s);
                        }
                    });
                }
            });
        }else{
            item_newfriend_add.setText("已添加");
            item_newfriend_add.setEnabled(false);
//            holder.setText(R.id.btn_aggree,"已添加");
//            holder.setEnabled(R.id.btn_aggree,false);
        }
    }

    /**
     * 添加到好友表中...
     * @param add
     * @param listener
     */
    private void agreeAdd(final NewFriend add, final SaveListener listener){
        User user =new User();
        user.setObjectId(add.getUid());
        UserModel.getInstance().agreeAddFriend(user, new SaveListener() {
            @Override
            public void onSuccess() {
                sendAgreeAddFriendMessage(add, listener);
            }

            @Override
            public void onFailure(int i, String s) {
                listener.onFailure(i, s);
            }
        });
    }

    /**
     * 发送同意添加好友的请求
     */
    private void sendAgreeAddFriendMessage(final NewFriend add,final SaveListener listener){
        BmobIMUserInfo info = new BmobIMUserInfo(add.getUid(), add.getName(), add.getAvatar());
        //如果为true,则表明为暂态会话，也就是说该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info,true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
        //而AgreeAddFriendMessage的isTransient设置为false，表明我希望在对方的会话数据库中保存该类型的消息
        AgreeAddFriendMessage msg =new AgreeAddFriendMessage();
        User currentUser = BmobUser.getCurrentUser(context, User.class);
        msg.setContent("我通过了你的好友验证请求，我们可以开始聊天了!");//---这句话是直接存储到对方的消息表中的
        Map<String,Object> map =new HashMap<>();
        map.put("msg",currentUser.getUsername()+"同意添加你为好友");//显示在通知栏上面的内容
        map.put("uid",add.getUid());//发送者的uid-方便请求添加的发送方找到该条添加好友的请求
        map.put("time", add.getTime());//添加好友的请求时间
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e){
                if (e == null) {//发送成功
                    //修改本地的好友请求记录
                    NewFriendManager.getInstance(context).updateNewFriend(add,Config.STATUS_VERIFIED);
                    listener.onSuccess();
                } else {//发送失败
                    listener.onFailure(e.getErrorCode(),e.getMessage());
                }
            }
        });
    }


}
