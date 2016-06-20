package com.personage.myolq.mvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.mvp.view.adapter.HomeViewPager;
import com.personage.myolq.mvp.view.fragment.ConversationFragment;
import com.personage.myolq.mvp.view.fragment.FriendsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.listener.ObseverListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import de.greenrobot.event.EventBus;


public class HomePageActivity extends InitActivity
        implements NavigationView.OnNavigationItemSelectedListener , ObseverListener {

    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.home_page_vp)
    ViewPager homePageVp;
    @Bind(R.id.home_page_tab)
    TabLayout homePageTab;
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);

        getFragmentViews();
        homeViewPager = new HomeViewPager(getSupportFragmentManager(),fragments,this);
        homePageVp.setAdapter(homeViewPager);
        homePageTab.setupWithViewPager(homePageVp);
        homePageTab.setTabsFromPagerAdapter(homeViewPager);

    }

    @Override
    protected void getonCreate() {
//        User user = BmobUser.getCurrentUser(this,User.class);
//        BmobIM.connect(user.getObjectId(), new ConnectListener() {
//            @Override
//            public void done(String uid, BmobException e) {
//                if (e == null) {
//                    Logger.i("connect success");
//                    //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
//                    EventBus.getDefault().post(new RefreshEvent());
//                } else {
//                    Logger.e(e.getErrorCode() + "/" + e.getMessage());
//                }
//            }
//        });
//        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
//        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
//            @Override
//            public void onChange(ConnectionStatus status) {
//                toast("" + status.getMsg());
//            }
//        });
//        //解决leancanary提示InputMethodManager内存泄露的问题
//        IMMLeaks.fixFocusedViewLeak(getApplication());
    }



    private void getFragmentViews(){
        fragments = new ArrayList<>();
        fragments.add(new ConversationFragment());
        fragments.add(new FriendsFragment());
        fragments.add(new FriendsFragment());
        titles = new ArrayList<>();
        for (int i=0;i<3;i++){
            TabLayout.Tab tab=homePageTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(homeViewPager.getTabView(i));
            }
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
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