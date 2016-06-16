package com.personage.myolq.api;

import com.example.javabean.entity.BaseEntity;
import com.example.javabean.entity.rongyun.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public interface ServerApi {

    @FormUrlEncoded
    @POST(Server.REQ)
    Call<BaseEntity> getReg(@Field("email") String email, @Field("mobile") String mobile, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(Server.EMAIL_LOGIN_TOKEN)
    Call<BaseEntity<User>> getEmailLoginToken(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(Server.EMAIL_LOGIN_TOKEN)
    Observable<BaseEntity<User>> getEmailLoginToken1(@Field("email") String email, @Field("password") String password);


}
