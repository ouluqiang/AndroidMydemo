package com.personage.myolq.mvp.model.usermodelimpl;

import android.content.Context;

import com.example.javabean.entity.UserBmob;
import com.personage.myolq.base.BmobBackCall;
import com.personage.myolq.mvp.model.usermodel.UserModel;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class UserModelImpl implements UserModel {


    @Override
    public void getLogin(Context context, String username, String password, BmobBackCall backCall) {
        UserBmob userBmob = new UserBmob();
        userBmob.setUsername(username);
        userBmob.setPassword(password);
        userBmob.login(context, backCall);
    }

    @Override
    public void getRegister(Context context, String username, String password, String nickname, String mobile, String email, String sex, String age, BmobBackCall backCall) {
        UserBmob userBmob = new UserBmob();
        userBmob.setUsername(username);
        userBmob.setPassword(password);
        userBmob.setNickname(nickname);
        userBmob.setEmail(email);
        userBmob.setMobilePhoneNumber(mobile);
        userBmob.setSex(sex);
        userBmob.setAge(age);
        userBmob.signUp(context, backCall);
    }
}
