package com.personage.myolq.mvp.presenter.userpresenterimpl;

import android.content.Context;

import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.mvp.backcall.BmobBackCall;
import com.personage.myolq.mvp.model.usermodel.UserModel;
import com.personage.myolq.mvp.model.usermodelimpl.UserModelImpl;
import com.personage.myolq.mvp.presenter.userpresenter.UserPresenter;
import com.personage.myolq.mvp.view.activity.LoginActivity;
import com.personage.myolq.mvp.view.activity.RegisterActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class UserPresenterImpl implements UserPresenter {

    private UserModel userModel;
    private RegisterActivity registerActivity;
    private LoginActivity loginActivity;

    public UserPresenterImpl(RegisterActivity registerActivity) {
        if (userModel == null) {
            userModel = new UserModelImpl();
        }
        this.registerActivity = registerActivity;
    }

    public UserPresenterImpl(LoginActivity loginActivity) {
        if (userModel == null) {
            userModel = new UserModelImpl();
        }
        this.loginActivity = loginActivity;
    }

    @Override
    public void getLogin(String username, String password) {
        userModel.getLogin(loginActivity, username, password, new BmobBackCall() {
            @Override
            public void onSuccess() {
                loginActivity.onToast("登录成功");
                loginActivity.onSucceed(getCurrentUser());
            }

            @Override
            public void onFailure(int i, String s) {
                loginActivity.onToast("登录失败:"+i+"----"+s);
            }
        });
    }

    @Override
    public void getRegister(Context context, String username, String password, String nickname,
                            String mobile, String email, String sex,String age) {
        userModel.getRegister(context, username, password, nickname, mobile, email, sex,age, new BmobBackCall() {
            @Override
            public void onSuccess() {
                registerActivity.onToast("注册成功");
                registerActivity.onSucceed(null);
            }

            @Override
            public void onFailure(int i, String s) {
                registerActivity.onToast("注册失败:"+i+"----"+s);
            }
        });
    }

    public User getCurrentUser(){
        return BmobUser.getCurrentUser(loginActivity, User.class);
    }
}
