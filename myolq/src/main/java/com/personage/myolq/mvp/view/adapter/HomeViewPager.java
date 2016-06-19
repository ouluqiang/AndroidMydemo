package com.personage.myolq.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class HomeViewPager extends FragmentPagerAdapter{

    private List<Fragment> mViews;
    private List<String> mTitle;

    public HomeViewPager(FragmentManager fm,List<Fragment> views,List<String> title) {
        super(fm);
        mViews=views;
        mTitle=title;
    }


    @Override
    public Fragment getItem(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
