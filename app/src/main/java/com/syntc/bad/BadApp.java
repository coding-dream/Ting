package com.syntc.bad;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/3/1.
 */
public class BadApp extends Application {
    private BadApp instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();

    }

    private void init() {
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);// 禁止默认的页面统计方式，这样将不会再自动统计Activity。

    }

    public BadApp getInstance(){
        return instance;
    }

}
