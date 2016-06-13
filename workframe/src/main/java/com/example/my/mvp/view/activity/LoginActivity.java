package com.example.my.mvp.view.activity;

import android.content.Intent;
import android.widget.EditText;

import com.example.javabean.request.User;
import com.example.my.R;
import com.example.my.mvp.base.BaseActivity;
import com.example.my.mvp.iuiview.IUiLoginView;
import com.example.my.mvp.presenter.loginpresenter.LoginPresenter;
import com.example.my.mvp.presenter.loginpresenterimpl.LoginPresenterImpl;
import com.example.utils.StringUtils;
import com.example.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements IUiLoginView{


    @Bind(R.id.et_user)
    EditText etUser;
    @Bind(R.id.et_pass)
    EditText etPass;
    private LoginPresenter loginPresenter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_activity_login);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void getonCreate() {

    }


    @OnClick(R.id.tv_login)
    public void onlogin() {
        if(StringUtils.isNull(getUserName())){
            ToastUtil.show(getApplicationContext(),getString(R.string.username_isnull));
            return;
        }
        if(StringUtils.isNull(getUserPass())){
            ToastUtil.show(getApplicationContext(),getString(R.string.userpass_isnull));
            return;
        }
        loginPresenter.getEmailLoginToken(this, (User) getBean());

    }

    @OnClick(R.id.tv_register)
    public void onregister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onToast(String text) {

    }

    @Override
    public Object getBean() {
        return new User(getUserName(),getUserPass());
    }

    @Override
    public String getUserName() {
        return etUser.getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return etPass.getText().toString().trim();
    }

    @Override
    public void onLogin() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegister() {

    }

//    @Override
//    public User getBean() {
//        return new User(getUserName(),getUserPass());
//    }
}

