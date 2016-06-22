package com.personage.myolq.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.my.mvp.adapter.recycleadapter.DividerItemDecoration;
import com.example.my.mvp.adapter.recycleadapter.RecyclerAdapter;
import com.example.utils.ToastUtil;
import com.example.widget.rippleclick.Click_Text;
import com.example.widget.rippleclick.RippleButton;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.bmob.model.BaseModel;
import com.personage.myolq.bmob.model.UserModel;
import com.personage.myolq.mvp.view.adapter.AdditionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.listener.FindListener;

public class AdditionActivity extends InitActivity {

    @Bind(R.id.click_search)
    Click_Text click_search;
    @Bind(R.id.et_find_name)
    EditText et_find_name;
    @Bind(R.id.rc_view)
    RecyclerView rc_view;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    private AdditionAdapter adapter;
    private List<User> mData;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_addition);
        setTitle("添加好友");
        setLeftOnBack();

    }

    @Override
    protected void getonCreate() {
        if(mData==null){
            mData=new ArrayList<>();
        }
        if(adapter==null){
            adapter = new AdditionAdapter(this,mData);
        }
        rc_view.setLayoutManager(new LinearLayoutManager(this));
        rc_view.setItemAnimator(new DefaultItemAnimator());
        rc_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rc_view.setAdapter(adapter);
        sw_refresh.setEnabled(true);
        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                ToastUtil.show(getApplicationContext(),position+"");
                Bundle bundle = new Bundle();
                User user = adapter.getItem(position);
                bundle.putSerializable("u", user);
                startActivity(UserInfoActivity.class, bundle, false);
            }
        });
        click_search.setOnClickListener(new Click_Text.OnClickListener() {

            @Override
            public void onClick(View v) {
                query();
            }
        });
    }



    public void query(){
        String name =et_find_name.getText().toString();
        if(TextUtils.isEmpty(name)){
            toast("请填写用户名");
            sw_refresh.setRefreshing(false);
            return;
        }
        UserModel.getInstance().queryUsers(name, BaseModel.DEFAULT_LIMIT, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                sw_refresh.setRefreshing(false);
                adapter.setDatas(list);
            }

            @Override
            public void onError(int i, String s) {
                sw_refresh.setRefreshing(false);
                adapter.setDatas(null);
                toast(s + "(" + i + ")");
            }
        });
    }
}
