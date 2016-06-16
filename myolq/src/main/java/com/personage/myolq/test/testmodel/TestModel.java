package com.personage.myolq.test.testmodel;

import com.example.javabean.entity.BaseEntity;
import com.example.javabean.entity.rongyun.User;
import com.example.my.http.backcall.RBackCall;
import com.personage.myolq.test.RxBackCall;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface TestModel {

    public void getEmailLoginToken2(String email, String password, RxBackCall<BaseEntity<User>> backCall);


    public void getEmailLoginToken1(String email, String password, Subscriber<BaseEntity<User>> backCall);

    public void getEmailLoginToken(String email, String password, RBackCall<BaseEntity<User>> backCall);


    public void getReg(String email, String mobile, String username, String password, RBackCall backCall);



}
