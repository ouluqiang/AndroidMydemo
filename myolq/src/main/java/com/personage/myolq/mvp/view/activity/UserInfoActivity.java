package com.personage.myolq.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.widget.CircleImageView;
import com.example.widget.rippleclick.Click_Text;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.bmob.base.ImageLoaderFactory;
import com.personage.myolq.bmob.bean.AddFriendMessage;
import com.personage.myolq.bmob.bean.User;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class UserInfoActivity extends InitActivity {


    @Bind(R.id.civ_avator)
    CircleImageView civ_avator;
    @Bind(R.id.tv_info_username)
    TextView tvInfoUsername;
    @Bind(R.id.tv_info_nickname)
    TextView tvInfoNickname;
    @Bind(R.id.click_add)
    Click_Text clickAdd;
    @Bind(R.id.click_chat)
    Click_Text clickChat;
    private BmobIMUserInfo info;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_user_info);
        setTitle("个人资料");
        setLeftOnBack();
    }

    @Override
    protected void getonCreate() {
        User user=(User)getBundle().getSerializable("u");
        if(user.getObjectId().equals(getCurrentUid())){
            clickAdd.setVisibility(View.GONE);
            clickChat.setVisibility(View.GONE);
        }else{
            clickAdd.setVisibility(View.VISIBLE);
            clickChat.setVisibility(View.VISIBLE);
        }
        //构造聊天方的用户信息:传入用户id、用户名和用户头像三个参数
        info = new BmobIMUserInfo(user.getObjectId(),user.getUsername(),user.getAvatar());
        ImageLoaderFactory.getLoader().loadAvator(civ_avator,user.getAvatar(),R.mipmap.head);
        tvInfoUsername.setText(user.getUsername());
        tvInfoNickname.setText(user.getNickname());
        clickAdd.setOnClickListener(new Click_Text.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddFriendMessage();
            }
        });
        clickChat.setOnClickListener(new Click_Text.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChatClick();
            }
        });
    }

    public String getCurrentUid(){
        return BmobUser.getCurrentUser(this,User.class).getObjectId();
    }


    /**
     * 发送添加好友的请求
     */
    private void sendAddFriendMessage(){
        //启动一个会话，如果isTransient设置为true,则不会创建在本地会话表中创建记录，
        //设置isTransient设置为false,则会在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
        AddFriendMessage msg =new AddFriendMessage();
        User currentUser = BmobUser.getCurrentUser(this,User.class);
        msg.setContent("很高兴认识你，可以加个好友吗?");//给对方的一个留言信息
        Map<String,Object> map =new HashMap<>();
        map.put("name", currentUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
        map.put("avatar",currentUser.getAvatar());//发送者的头像
        map.put("uid",currentUser.getObjectId());//发送者的uid
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {
                if (e == null) {//发送成功
                    toast("好友请求发送成功，等待验证");
                } else {//发送失败
                    toast("发送失败:" + e.getMessage());
                }
            }
        });
    }

    private void onChatClick(){
        //启动一个会话，设置isTransient设置为false,则会在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info,false,null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("c", c);
        startActivity(ChatActivity.class, bundle, false);
    }

}
