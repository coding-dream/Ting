package com.syntc.bad.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.syntc.bad.AppManager;
import com.syntc.bad.R;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends AppCompatActivity {

    protected LayoutInflater mInflater;
    public boolean visible;
    protected String pageName = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBarTint();

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

    public void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true); //设置状态栏为透明的
            setStatusBarTintColor(getColorPrimary());  //设置状态栏颜色
        }
    }

    //设置状态栏为透明的
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    // 设置状态栏颜色
    public void setStatusBarTintColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 19
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(resId));
        }
    }


    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


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
