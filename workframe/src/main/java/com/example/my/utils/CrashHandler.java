package com.example.my.utils;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;


import com.example.my.mvp.base.BaseActivity;
import com.example.my.mvp.base.BaseApplication;


import static android.support.v7.app.AlertDialog.*;

/**
 * 异常崩溃处理类 当程序发生未捕获异常时由该类接受并处理
 *
 * @author
 */
public class CrashHandler implements UncaughtExceptionHandler {

    // 系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandle;
    private BaseApplication application;

    public CrashHandler(BaseApplication application) {
        this.application = application;
        mDefaultHandle = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandle != null) {
            mDefaultHandle.uncaughtException(thread, ex);
        } else {

        }

    }

    /**
     * @param ex 异常
     * @return true：如果处理了该异常信息；否则返回false。
     * @brief 自定义错误处理，收集错误信息
     * @details 发送错误报告等操作均在此完成
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        ex.printStackTrace();
        // 提示错误消息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                // Toast.makeText(application.getApplicationContext(), "应用发生异常",
                // Toast.LENGTH_LONG).show();
                showDialog();
                Looper.loop();
            }
        }.start();
        return true;
    }

    private void showDialog() {
        Activity activity = application.getActivity();
        if (null == activity) {
            return;
        }

//		new PromptDialog.Builder(application.getActivity())
//				.setMessage("程序发生异常,是否重新启动程序?").setCanceledOnTouchOutside(true)
//				.setButton1("否", new OnClickListener() {
//
//					@Override
//					public void onClick(Dialog dialog, int which) {
//						dialog.dismiss();
//						application.finishActivity();
//					}
//				}).setButton2("确定", new OnClickListener() {
//
//			@Override
//			public void onClick(Dialog dialog, int which) {
//				dialog.dismiss();
//				reStart();
//			}
//		}).setOnDismissListener(new OnDismissListener() {
//
//			@Override
//			public void onDismiss(DialogInterface dialog) {
//				reStart();
//			}
//		}).show();
        Builder builder = new Builder(
                application.getActivity());
        builder.setTitle("发生异常");
        builder.setMessage("程序发生异常,是否重新启动程序?");
        builder.setNegativeButton("是", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                reStart();
            }
        });
        builder.setPositiveButton("否", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                application.finishActivity();
            }
        });
        builder.create();
        builder.show();
    }

    private void reStart() {
        Intent intent = new Intent(application.getApplicationContext(),
                BaseActivity.class);
        PendingIntent restartIntent = PendingIntent.getActivity(
                application.getApplicationContext(), 0, intent,
                Intent.FLAG_ACTIVITY_NEW_TASK);
        // 退出程序
        AlarmManager mgr = (AlarmManager) application
                .getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent); // 1秒钟后重启应用
        application.finishActivity();
    }
}
