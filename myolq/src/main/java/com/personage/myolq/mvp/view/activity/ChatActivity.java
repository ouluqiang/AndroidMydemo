package com.personage.myolq.mvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.my.mvp.base.BaseActivity;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;

public class ChatActivity extends InitActivity {


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_chat);
        setTitle("聊天");
        setLeftOnBack();
    }

    @Override
    protected void getonCreate() {

    }
}
