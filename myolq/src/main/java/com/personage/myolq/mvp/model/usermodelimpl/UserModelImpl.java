package com.personage.myolq.mvp.model.usermodelimpl;

import android.content.Context;

import com.personage.myolq.mvp.backcall.BmobBackCall;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.mvp.model.usermodel.UserModel;


/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class UserModelImpl implements UserModel {


    @Override
    public void getLogin(Context context, String username, String password, BmobBackCall backCall) {
        User userBmob = new User();
        userBmob.setUsername(username);
        userBmob.setPassword(password);
        userBmob.login(context, backCall);
    }

    @Override
    public void getRegister(Context context, String username, String password, String nickname, String mobile, String email, String sex, String age, BmobBackCall backCall) {
        User userBmob = new User();
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
