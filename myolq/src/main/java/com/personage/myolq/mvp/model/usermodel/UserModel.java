package com.personage.myolq.mvp.model.usermodel;

import android.content.Context;

import com.personage.myolq.base.BmobBackCall;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface UserModel {

    public void getLogin(Context context, String username, String password, BmobBackCall backCall);

    public void getRegister(Context context,String username,String password,String nickname,
                            String mobile,String email,String sex,String age,BmobBackCall backCall);

}
