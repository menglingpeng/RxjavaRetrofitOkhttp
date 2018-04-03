package com.menglingpeng.httputil.base;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.load.HttpException;
import com.menglingpeng.httputil.R;
import com.menglingpeng.httputil.utils.ExceptionUtil;
import com.menglingpeng.httputil.utils.NetWorkUtil;
import com.menglingpeng.httputil.utils.ToastUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by mengdroid on 2018/4/3.
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    private Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Subscription s) {
        Toast.makeText(context, "http is start", Toast.LENGTH_SHORT).show();
        if (!NetWorkUtil.isNetWorkConnected(context)) {
            Toast.makeText(context, context.getString(R.string.toast_base_subscriber_network_disable_text),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {
        Toast.makeText(context, "http is Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable t) {
        //自定义异常处理
        if (t instanceof UnknownHostException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_unknown_toast_text));
        } else if (t instanceof SocketTimeoutException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_time_out_toast_text));
        } else if (t instanceof ConnectException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_connect_toast_text));
        } else if (t instanceof HttpException) {
            ToastUtil.showLongToast(context.getString(R.string.exception_http_toast_text));
        }else {
            ToastUtil.showLongToast(context.getString(R.string.exception__toast_text));
        }
    }

    public abstract void onError(ExceptionUtil.ResponseThrowable e);
}
