package com.syntc.bad;

import android.app.Application;

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

    }

    public BadApp getInstance(){
        return instance;
    }

}
