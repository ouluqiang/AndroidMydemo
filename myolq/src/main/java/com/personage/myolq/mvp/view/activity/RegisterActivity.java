package com.personage.myolq.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.EditText;

import com.example.utils.L;
import com.example.utils.MD5Util;
import com.example.utils.StringUtils;
import com.example.utils.ToastUtil;
import com.example.widget.rippleclick.Click_Text;
import com.personage.myolq.R;
import com.personage.myolq.base.InitActivity;
import com.personage.myolq.mvp.iuiview.IUiRegisterView;
import com.personage.myolq.mvp.presenter.userpresenter.UserPresenter;
import com.personage.myolq.mvp.presenter.userpresenterimpl.UserPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends InitActivity implements IUiRegisterView {


    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password1)
    EditText etPassword1;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.radio_man)
    AppCompatRadioButton radioMan;
    @Bind(R.id.radio_woman)
    AppCompatRadioButton radioWoman;
    @Bind(R.id.et_age)
    EditText etAge;
    private UserPresenter mUserPresenter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_register);
        setTitle(R.string.title_activity_register);
        setLeftOnBack();
        mUserPresenter = new UserPresenterImpl(this);
    }

    @Override
    protected void getonCreate() {
        radioMan.setChecked(true);
        Click_Text click_register= getFindViewByid(R.id.click_register);
        click_register.setOnClickListener(new Click_Text.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });
    }


    public void onRegister() {
        if (StringUtils.isNull(getUserName())) {
            ToastUtil.show(this, getString(R.string.username_isnull));
            return;
        }
        if (StringUtils.isNull(getPassWord())) {
            ToastUtil.show(this, getString(R.string.userpass_isnull));
            return;
        }
        if (StringUtils.isNull(getPassWord1())) {
            ToastUtil.show(this, getString(R.string.userpass_isnull));
            return;
        }
        if(!StringUtils.isLengthMin(getPassWord(),6)||!StringUtils.isLengthMin(getPassWord1(),6)){
            ToastUtil.show(this, getString(R.string.userpass_ismin));
            return;
        }
        if (StringUtils.isEquals(getPassWord(), getPassWord1())) {
            ToastUtil.show(this, getString(R.string.userpass_isequest));
            return;
        }
        if (StringUtils.isNull(getNickName())) {
            ToastUtil.show(this, getString(R.string.nickname_isnull));
            return;
        }
        if (StringUtils.isNull(getEmail())) {
            ToastUtil.show(this, getString(R.string.email_isnull));
            return;
        }
        if (StringUtils.isNull(getMobile())) {
            ToastUtil.show(this, getString(R.string.mobile_isnull));
            return;
        }
        L.log(getSex()+"---"+MD5Util.MD5(getPassWord())+"---"+getPassWord());
        L.log(getAge()+"---"+getEmail() + "++" + getMobile() + "++" + getUserName() + "++" + getPassWord());
        mUserPresenter.getRegister(this,getUserName(), MD5Util.MD5(getPassWord()),getNickName(),getMobile(),getEmail(),getSex(),getAge());
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getPassWord1() {
        return etPassword1.getText().toString().trim();
    }


    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getMobile() {
        return etMobile.getText().toString().trim();
    }

    @Override
    public String getAge() {
        return etAge.getText().toString().trim();
    }

    @Override
    public String getSex() {
        return radioWoman.isChecked()?"女":"男";
    }

    @Override
    public String getNickName() {
        return etNickname.getText().toString().trim();
    }


    @Override
    public void onSucceed(Object object) {
        finish();
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

