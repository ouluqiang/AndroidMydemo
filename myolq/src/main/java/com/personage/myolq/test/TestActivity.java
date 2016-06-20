package com.personage.myolq.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.javabean.entity.rongyun.UserRong;
import com.example.my.mvp.base.BaseActivity;
import com.example.utils.StringUtils;
import com.example.utils.ToastUtil;
import com.personage.myolq.R;
import com.personage.myolq.mvp.view.activity.HomePageActivity;
import com.personage.myolq.test.iuiview.IUiTestView;
import com.personage.myolq.test.testpresenter.TestPresenter;
import com.personage.myolq.test.testpresenterimpl.TestPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class TestActivity extends BaseActivity implements IUiTestView {


    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.til_username)
    TextInputLayout tilUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.til_password)
    TextInputLayout tilPassword;
    private TestPresenter loginPresenter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_test);
        setTitle(R.string.title_activity_login);
        loginPresenter = new TestPresenterImpl(this);
    }

    @Override
    protected void getonCreate() {
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    tilUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick(R.id.tv_login)
    public void onlogin() {
        if (StringUtils.isNull(getUserName())) {
//            ToastUtil.show(getApplicationContext(), getString(R.string.username_isnull));
            tilUsername.setError(getString(R.string.username_isnull));
            return;
        }
        if (StringUtils.isNull(getUserPass())) {
//            ToastUtil.show(getApplicationContext(), getString(R.string.userpass_isnull));
            tilPassword.setError(getString(R.string.userpass_isnull));
        }else{
            tilUsername.setErrorEnabled(false);
            tilPassword.setErrorEnabled(false);
            loginPresenter.getEmailLoginToken2(this, getUserName(),getUserPass());
        }


    }

    @OnClick(R.id.tv_register)
    public void onregister() {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onSucceed(Object object) {
        UserRong user= (UserRong) object;
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onToast(String text) {
        ToastUtil.show(this,text);
    }


    @Override
    public String getUserName() {
        return tilUsername.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return tilPassword.getEditText().getText().toString().trim();
    }

    @Override
    public void onLogin() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegister() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

