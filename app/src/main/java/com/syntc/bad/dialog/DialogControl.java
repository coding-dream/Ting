package com.syntc.bad.dialog;

import android.app.ProgressDialog;

/**
 * @author limitless create on 2017/3/5 17:25
 * @version 1.0.0
 */
public interface DialogControl {

    void hideWaitDialog();

    ProgressDialog showWaitDialog();

    ProgressDialog showWaitDialog(int resid);

    ProgressDialog showWaitDialog(String text);
}
