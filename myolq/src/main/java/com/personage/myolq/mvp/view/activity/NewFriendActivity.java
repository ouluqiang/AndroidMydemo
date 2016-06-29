package com.personage.myolq.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.my.mvp.adapter.recycleadapter.DividerItemDecoration;
import com.example.my.mvp.adapter.recycleadapter.RecyclerAdapter;
import com.example.utils.ToastUtil;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.bmob.db.NewFriend;
import com.personage.myolq.bmob.db.NewFriendManager;
import com.personage.myolq.mvp.view.adapter.NewFriendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewFriendActivity extends InitActivity {

    @Bind(R.id.rc_view)
    RecyclerView rcView;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout swRefresh;
    private List<NewFriend> mDate;
    private NewFriendAdapter adapter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_new_friend);
        setTitle("新朋友");
        setLeftOnBack();
    }

    @Override
    protected void getonCreate() {
        if (mDate == null) {
            mDate = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new NewFriendAdapter(this, mDate);
        }
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setItemAnimator(new DefaultItemAnimator());
        rcView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcView.setAdapter(adapter);
        swRefresh.setEnabled(true);
        //批量更新未读未认证的消息为已读状态
        NewFriendManager.getInstance(this).updateBatchStatus();
        setListener();
    }

    private void setListener(){

        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        swRefresh.setRefreshing(true);
        query();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     查询本地会话
     */
    public void query(){
        ToastUtil.show(this,NewFriendManager.getInstance(this).getAllNewFriend().size()+"");
        adapter.setDatas(NewFriendManager.getInstance(this).getAllNewFriend());
//        adapter.notifyDataSetChanged();
        swRefresh.setRefreshing(false);
    }
}
