package com.example.bmobsdk.bmob;

import android.content.Context;

import cn.bmob.newim.BmobIM;

/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class BmobIm {

    public static void getBmobIM(Context context){
        //NewIM初始化
        BmobIM.init(context);
        //注册消息接收器
        BmobIM.registerDefaultMessageHandler(new MessageHandler(context));
    }

}
