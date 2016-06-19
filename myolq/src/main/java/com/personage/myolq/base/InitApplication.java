package com.personage.myolq.base;

import android.content.Context;

import com.example.bmobsdk.BmobIm;
import com.example.my.mvp.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class InitApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //im初始化
            BmobIm.getBmobIM(this);
        }

    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
