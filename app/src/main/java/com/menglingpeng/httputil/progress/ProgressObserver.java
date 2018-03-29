package com.menglingpeng.httputil.progress;

import android.content.Context;

import com.bumptech.glide.load.HttpException;
import com.menglingpeng.httputil.R;
import com.menglingpeng.httputil.utils.ExceptionUtil;
import com.menglingpeng.httputil.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by mengdroid on 2018/3/25.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private ObserverResponseListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog,
                            boolean cancelable){
        this.listener = listener;
        this.context = context;
        if(isDialog){
            mProgressDialogHandler = new ProgressDialogHandler(context, this,
                    cancelable);
        }
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        //自定义异常处理
        if(e instanceof ExceptionUtil.ResponseThrowable){
            listener.onError((ExceptionUtil.ResponseThrowable)e);
        } else {
            listener.onError(new ExceptionUtil.ResponseThrowable(e, ExceptionUtil.ERROR.UNKNOWN));
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_unknown_toast_text));
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_time_out_toast_text));
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_connect_toast_text));
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_http_toast_text));
        }else {
            ToastUtil.showLongToast(context.getString(R.string.exception__toast_text));
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
        if(!d.isDisposed()){
            d.dispose();
        }
    }
}
