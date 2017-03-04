package com.syntc.bad;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.umeng.analytics.MobclickAgent;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/3/1.
 */
public class BadApp extends Application {
    private BadApp instance ;

    @Override
    public void onCreate() {
        super.onCreate();

        if (isCurrentProcess()) {
            instance = this;
            JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
            JPushInterface.init(this);     		// 初始化 JPush

            MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
            MobclickAgent.openActivityDurationTrack(false);// 禁止默认的页面统计方式，这样将不会再自动统计Activity。

        }

    }

    public BadApp getInstance(){
        return instance;
    }


    /**
     * 私有方法尽量放在最后面
     * @return  是否是当前进程(防止跨进程通信 不必要的重复初始化)
     */
    private boolean isCurrentProcess() {
        String processName = getProcessName(this, Process.myPid());
        if (processName == null || !processName.equals(BuildConfig.APPLICATION_ID)) {
            return false;
        }
        return true;
    }

    private String getProcessName(Context cxt, int pid) {
        List<ActivityManager.RunningAppProcessInfo> runningApps = ((ActivityManager) cxt.getSystemService(ACTIVITY_SERVICE)).getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


}
