package com.syntc.bad.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.syntc.bad.R;
import com.syntc.bad.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

public class JpushNotifyAcitivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_jpush_notify_acitivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();

            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
        }

    }
}
