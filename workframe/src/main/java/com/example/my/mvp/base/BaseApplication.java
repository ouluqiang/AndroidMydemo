package com.example.my.mvp.base;

import android.app.Activity;
import android.app.Application;
import android.os.Process;


import com.example.my.http.ImageManager;
import com.example.my.imkit.RongCloud;
import com.example.my.utils.CrashHandler;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/5/16.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private static LinkedList<Activity> activityList;
    private Activity activity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler mCrashHandler = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(mCrashHandler);
        activityList = new LinkedList<Activity>();
        ImageManager.getInstace().init(this);
        //融云
        RongCloud.onRongIM(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public synchronized void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean isContain(Activity activity) {
        return BaseApplication.activityList.contains(activity);
    }

    public int activitySize() {
        return BaseApplication.activityList.size();
    }

    public Activity getActivity() {
        if (activity == null && activityList.size()>0) {
            activity = activityList.getLast();
        }
        return activity;
    }

    public LinkedList<Activity> getActivityList() {
        return activityList;
    }

    /**
     * 向activityList中添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityList == null)
            activityList = new LinkedList<Activity>();
        activityList.add(activity);
    }

    /**
     * 从activityList中移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityList != null && activityList.contains(activity)) {
            activity.finish();
            activityList.remove(activity);
        }
    }

    /**
     * 关闭该应用进程
     */
    public void finishActivity() {
        for (Activity activity : activityList) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityList.clear();
        Process.killProcess(Process.myPid());
    }




}
