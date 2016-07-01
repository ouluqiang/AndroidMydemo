package imbmob.test.testmodel;


import imbmob.test.RxBackCall;
import imbmob.test.http.backcall.RBackCall;
import imbmob.test.rongyun.BaseEntity;
import imbmob.test.rongyun.UserRong;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface TestModel {

    public void getEmailLoginToken2(String email, String password, RxBackCall<BaseEntity<UserRong>> backCall);


    public void getEmailLoginToken1(String email, String password, Subscriber<BaseEntity<UserRong>> backCall);

    public void getEmailLoginToken(String email, String password, RBackCall<BaseEntity<UserRong>> backCall);


    public void getReg(String email, String mobile, String username, String password, RBackCall backCall);



}
