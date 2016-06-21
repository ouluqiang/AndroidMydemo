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
    private List<View> mTitle;
    private String tabTitles[] = new String[]{"TAB1","TAB2","TAB3"};
    private Context mContext;

    public HomeViewPager(FragmentManager fm,List<Fragment> views,List<View> title) {
        super(fm);
        mViews=views;
        mTitle=title;
    }

    public HomeViewPager(FragmentManager fm,List<Fragment> views,Context context) {
        super(fm);
        mViews=views;
        mContext=context;
    }

    public View getTabView(int position) {
        View tabview= LayoutInflater.from(mContext).inflate(R.layout.tab_item_home, null);
        ImageView iv_tab_icon= (ImageView) tabview.findViewById(R.id.iv_tab_icon);
        TextView tv_tab_title= (TextView) tabview.findViewById(R.id.tv_tab_title);
        tv_tab_title.setText("title");
        return tabview;
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
        return tabTitles[position];
    }
}
