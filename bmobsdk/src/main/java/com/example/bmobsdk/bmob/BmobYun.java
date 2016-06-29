package com.example.bmobsdk.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class BmobYun {

//    public static String APPKEY="31b692ca8ff5535b17d19fd2e8445d47";
//
//    public static void getBmobInit(boolean falg, Context context){
//        if(falg){
//            //提供以下两种方式进行初始化操作：
//
//            //第一：默认初始化
//            Bmob.initialize(context, APPKEY);
//
//
//        }else{
//            //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
//            BmobConfig config =new BmobConfig.Builder(context)
//            //设置appkey
//            .setApplicationId(APPKEY)
//            //请求超时时间（单位为秒）：默认15s
//            .setConnectTimeout(30)
//            //文件分片上传时每片的大小（单位字节），默认512*1024
//            .setUploadBlockSize(1024*1024)
//            //文件的过期时间(单位为秒)：默认1800s
//            .setFileExpiration(2500)
//            .build();
//            Bmob.initialize(config);
//        }
//    }

}
