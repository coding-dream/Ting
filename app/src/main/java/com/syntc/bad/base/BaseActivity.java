package com.syntc.bad.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.syntc.bad.AppManager;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends AppCompatActivity {

    protected LayoutInflater mInflater;
    protected boolean visible;
    protected String pageName = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getInstance().addActivity(this);

        mInflater = getLayoutInflater();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        initView();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();



    @Override
    protected void onResume() {
        super.onResume();
        visible = true;
        MobclickAgent.onPageStart(pageName); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长

    }

    @Override
    protected void onPause() {
        super.onPause();
        visible = false;

        MobclickAgent.onPageEnd(pageName); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }
}
