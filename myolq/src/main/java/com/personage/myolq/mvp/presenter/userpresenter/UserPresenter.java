package com.personage.myolq.mvp.presenter.userpresenter;

import android.content.Context;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface UserPresenter {

    public void getLogin(String username,String password);

    public void getRegister(Context context,String username,String password,String nickname,
                            String mobile,String email,String sex,String age);



}
