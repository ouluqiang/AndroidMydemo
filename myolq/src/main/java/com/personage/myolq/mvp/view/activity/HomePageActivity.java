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
import android.view.Menu;
import android.view.MenuItem;
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


public class HomePageActivity extends InitActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
    private List<String> titles;

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
        HomeViewPager homeViewPager=new HomeViewPager(getSupportFragmentManager(),fragments,titles);
        homePageVp.setAdapter(homeViewPager);
        homePageTab.setupWithViewPager(homePageVp);
        homePageTab.setTabsFromPagerAdapter(homeViewPager);

    }

    @Override
    protected void getonCreate() {

    }



    private void getFragmentViews(){
        fragments = new ArrayList<>();
        fragments.add(new ConversationFragment());
        fragments.add(new FriendsFragment());
        fragments.add(new FriendsFragment());
        titles = new ArrayList<>();
        titles.add("tab1");
        titles.add("tab2");
        titles.add("tab3");
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