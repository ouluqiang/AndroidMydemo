package com.personage.myolq.api;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class OnResponse {

    public static boolean is_OK(String s){
        return s.equals(Server.RESPONSE_OK)?true:false;
    }

}
