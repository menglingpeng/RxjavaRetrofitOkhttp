package com.menglingpeng.httputil.progress;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by mengdroid on 2018/3/25.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private ObserverResponseListener listener;
    private Context context;
    private Disposable d;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog,
                            boolean cancelable){
        this.listener = listener;
        this.context = context;
    }

    private void showProgressDialog(){

    }

    private void dismissProgressDialog(){

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
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancleProgress() {
        //如果处于订阅状态，则取消订阅
        if(!d.isDisposed()){
            d.dispose();
        }
    }
}
