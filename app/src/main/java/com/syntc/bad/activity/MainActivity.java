package com.syntc.bad.activity;

import android.view.View;

import com.syntc.bad.R;
import com.syntc.bad.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:

                break;
            case R.id.btn_set_repository:

            default:
                break;
        }
    }
}
