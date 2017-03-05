package com.syntc.bad.activity;

import android.content.Intent;
import android.view.View;

import com.syntc.bad.R;
import com.syntc.bad.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }


    public void handleEvent(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AboutActivity.class);
        startActivity(intent);

    }
}
