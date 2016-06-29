package com.personage.myolq.base;

import com.example.my.mvp.base.BaseApplication;
import com.personage.myolq.bmob.DemoMessageHandler;
import com.personage.myolq.bmob.base.UniversalImageLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class InitApplication extends BaseApplication {


    private static InitApplication INSTANCE;
    public static InitApplication INSTANCE(){
        return INSTANCE;
    }
    private void setInstance(InitApplication app) {
        setBmobIMApplication(app);
    }
    private static void setBmobIMApplication(InitApplication a) {
        InitApplication.INSTANCE = a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //im初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
        }
        UniversalImageLoader.initImageLoader(this);
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
