package com.personage.myolq.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.widget.CircleImageView;
import com.orhanobut.logger.Logger;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.bmob.db.NewFriendManager;
import com.personage.myolq.bmob.event.RefreshEvent;
import com.personage.myolq.bmob.util.IMMLeaks;
import com.personage.myolq.mvp.view.adapter.HomeViewPager;
import com.personage.myolq.mvp.view.fragment.ConversationFragment;
import com.personage.myolq.mvp.view.fragment.FriendsFragment;
import com.personage.myolq.mvp.view.fragment.SetFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.listener.ObseverListener;
import cn.bmob.newim.notification.BmobNotificationManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;


public class HomePageActivity extends InitActivity
        implements NavigationView.OnNavigationItemSelectedListener , ObseverListener {

    @Bind(R.id.civ_avator)
    CircleImageView civ_avator;
    @Bind(R.id.iv_conversation_tips)
    ImageView iv_conversation_tips;
    @Bind(R.id.iv_set_tips)
    ImageView iv_set_tips;

    @Bind(R.id.btn_conversation)
    Button btn_conversation;
    @Bind(R.id.btn_contact)
    Button btn_contact;
    @Bind(R.id.btn_set)
    Button btn_set;
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.home_page_vp)
    ViewPager homePageVp;

    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private List<Fragment> fragments;
    private List<View> titles;
    private HomeViewPager homeViewPager;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        navView.setNavigationItemSelectedListener(this);

        getFragmentViews();
        homeViewPager = new HomeViewPager(getSupportFragmentManager(),fragments,this);
        homePageVp.setAdapter(homeViewPager);
        civ_avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected void getonCreate() {
        User user = BmobUser.getCurrentUser(this,User.class);
        BmobIM.connect(user.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Logger.i("connect success");
                    //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                    EventBus.getDefault().post(new RefreshEvent());
                } else {
                    Logger.e(e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });
        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                toast("" + status.getMsg());
            }
        });
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());

    }

    @OnClick(R.id.add)
    public void onAdd(){
        startActivity(new Intent(this,AdditionActivity.class));
    }

    @OnClick(R.id.btn_conversation)
    public void onViewOne(){
        topTitle.setText("会话");
        homePageVp.setCurrentItem(0);
    }
    @OnClick(R.id.btn_contact)
    public void onViewTwo(){
        topTitle.setText("联系人");
        homePageVp.setCurrentItem(1);
    }

    @OnClick(R.id.btn_set)
    public void onViewThree(){
        topTitle.setText("设置");
        homePageVp.setCurrentItem(2);
    }

    private void getFragmentViews(){
        fragments = new ArrayList<>();
        fragments.add(new ConversationFragment());
        fragments.add(new FriendsFragment());
        fragments.add(new SetFragment());

    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示小红点
        checkRedPoint();
        //进入应用后，通知栏应取消
        BmobNotificationManager.getInstance(this).cancelNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清理导致内存泄露的资源
        BmobIM.getInstance().clear();
    }

    /**注册消息接收事件
     * @param event
     */
    @Subscribe
    public void onEventMainThread(MessageEvent event){
        checkRedPoint();
    }

    /**注册离线消息接收事件
     * @param event
     */
    @Subscribe
    public void onEventMainThread(OfflineMessageEvent event){
        checkRedPoint();
    }

    /**注册自定义消息接收事件
     * @param event
     */
    @Subscribe
    public void onEventMainThread(RefreshEvent event){
        log("---主页接收到自定义消息---");
        checkRedPoint();
    }

    private void checkRedPoint(){
        int count = (int)BmobIM.getInstance().getAllUnReadCount();
        if(count>0){
            iv_conversation_tips.setVisibility(View.VISIBLE);
        }else{
            iv_conversation_tips.setVisibility(View.GONE);
        }
//        //是否有好友添加的请求
        if(NewFriendManager.getInstance(this).hasNewFriendInvitation()){
            iv_set_tips.setVisibility(View.VISIBLE);
        }else{
            iv_set_tips.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            onExit();
//            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}