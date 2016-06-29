package com.personage.myolq.mvp.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.personage.myolq.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class HomeViewPager extends FragmentPagerAdapter{

    private List<Fragment> mViews;
    private Context mContext;

    public HomeViewPager(FragmentManager fm,List<Fragment> views) {
        super(fm);
        mViews=views;
    }

    public HomeViewPager(FragmentManager fm,List<Fragment> views,Context context) {
        super(fm);
        mViews=views;
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

}
