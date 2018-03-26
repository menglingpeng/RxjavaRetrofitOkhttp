package com.menglingpeng.httputil.utils;

import android.content.Context;

import com.menglingpeng.httputil.R;

import retrofit2.HttpException;

/**
 * Created by mengdroid on 2018/3/26.
 */

public class ExceptionUtil {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable geException(Context context, Throwable e){
        if(e instanceof HttpException) {
            ResponeThrowable re;
            re = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            HttpException httpException = (HttpException) e;
            switch (httpException.code()){
                case UNAUTHORIZED:
                    break;
                case FORBIDDEN:
                    break;
                case NOT_FOUND:
                    break;
                case REQUEST_TIMEOUT:
                    break;
                case INTERNAL_SERVER_ERROR:
                    break;
                case BAD_GATEWAY:
                    break;
                case SERVICE_UNAVAILABLE:
                    break;
                case GATEWAY_TIMEOUT:
                    break;
                default:
                    re.message = context.getString(R.string.exception_http_error);
                    break;
            }
            return re;
        }

    }

    /**
     * 约定异常类型
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORK_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}
