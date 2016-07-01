package com.personage.myolq.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.utils.MD5Util;
import com.example.utils.StringUtils;
import com.example.utils.ToastUtil;
import com.example.widget.rippleclick.Click_Text;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.mvp.iuiview.IUiLoginView;
import com.personage.myolq.mvp.presenter.userpresenter.UserPresenter;
import com.personage.myolq.mvp.presenter.userpresenterimpl.UserPresenterImpl;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends InitActivity implements IUiLoginView {


    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    private UserPresenter userPresenter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_activity_login);
        setLeftOnBack();
        userPresenter = new UserPresenterImpl(this);

    }

    @Override
    protected void getonCreate() {
        Click_Text click_login = getFindViewByid(R.id.click_login);
        click_login.setOnClickListener(new Click_Text.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();

            }
        });
    }

//    @OnClick(R.id.tv_forgetpass)
//    public void onforgetpass() {
//        startActivity(new Intent(this, HomePageActivity.class));
//    }

    @OnClick(R.id.tv_register)
    public void onregister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void onLogin() {
        if (StringUtils.isNull(getUserName())) {
            ToastUtil.show(this, getString(R.string.username_isnull));
            return;
        }
        if (StringUtils.isNull(getUserPass())) {
            ToastUtil.show(this, getString(R.string.userpass_isnull));
            return;
        }
        userPresenter.getLogin(getUserName(), MD5Util.MD5(getUserPass()));
    }

    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void onSucceed(Object object) {
        User user = (User) object;
        BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
        startActivity(HomePageActivity.class, null, true);
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onToast(String text) {
        ToastUtil.show(this, text);
    }
}

