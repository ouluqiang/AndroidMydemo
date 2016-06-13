package com.example.my.api;

import com.example.javabean.entity.BaseEntity;
import com.example.javabean.request.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public interface ServerApi {

    @POST(Server.REQ)
    Call<BaseEntity> getReg(@Body User user);

    @POST(Server.EMAIL_LOGIN_TOKEN)
    Call<BaseEntity> getEmailLoginToken(@Body User user);


}
