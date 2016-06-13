package com.example.my.mvp.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.my.R;
import com.example.my.widget.Top_Title;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/17.
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Top_Title toolbarTop;
    private LinearLayout body;

    /*
                 * 设置布局
                 */
//    protected abstract int getLayout();
    protected abstract void setLayout();

    /**
     * onCreate方法
     *
     * @param
     */
    protected abstract void getonCreate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(android.R.color.transparent);
//        }

        BaseApplication.getInstance().addActivity(this);
        BaseApplication.getInstance().setActivity(this);
        setContentView(R.layout.base_layout);
        toolbarTop = (Top_Title) findViewById(R.id.toolbar_top);
        body = (LinearLayout) findViewById(R.id.body);
        if(getSupportActionBar()==null){
            setSupportActionBar(toolbarTop.getToolbar());
        }

        setLayout();
        getonCreate();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getInstance().setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        BaseApplication.getInstance().removeActivity(this);
        BaseApplication.getInstance().setActivity(null);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.base_layout)
            super.setContentView(layoutResID);
        else if (layoutResID == R.layout.activity_home_page){
            super.setContentView(layoutResID);
        } else {
            getLayoutInflater().inflate(layoutResID, body);
            ButterKnife.bind(this);
            getonCreate();

        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        body.removeAllViews();
        body.addView(view, params);
        getonCreate();
    }

    @Override
    public void setContentView(View view) {
        body.removeAllViews();
        body.addView(view);
        getonCreate();
    }

    public void setContentView(int layoutResId, boolean isShowTitle) {
        if (!isShowTitle) {
            toolbarTop.setVisibility(View.GONE);
        }
        this.setContentView(layoutResId);
    }

    public void setTitle(CharSequence text){
        toolbarTop.setTitle(text);
    }
    public void setTitle(int text){
        toolbarTop.setTitle(getString(text));
    }
}
