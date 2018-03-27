package com.menglingpeng.httputil.progress;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by mengdroid on 2018/3/27.
 */

public class ProgressDialogHandler extends android.os.Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private SweetAlertDialog sad;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void showProgressDialog() {
        if (sad == null) {
            sad = new SweetAlertDialog(context);
            sad.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
            sad.setTitleText("正在加载...");
            sad.setCancelable(cancelable);

            if (cancelable) {
                sad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!sad.isShowing()) {
                sad.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (sad != null) {
            sad.dismiss();
            sad = null;
        }
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
