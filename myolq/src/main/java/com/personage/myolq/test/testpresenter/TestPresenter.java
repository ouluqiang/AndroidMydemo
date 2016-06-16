package com.personage.myolq.test.testpresenter;

import android.content.Context;



/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface TestPresenter {

    public void getEmailLoginToken2(Context context, String email, String password);

    public void getEmailLoginToken1(Context context, String email, String password);

    public void getEmailLoginToken(Context context, String email, String password);

    public void getReg(Context context, String email, String mobile, String username, String password);


}
