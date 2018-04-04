package com.menglingpeng.httputil.http;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by mengdroid on 2018/4/4.
 */

public class DownLoadManager {

    private CallBack callBack;
    private Handler handler;
    private static DownLoadManager downLoadManager;
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";

    private static String PNG_CONTENTTYPE = "image/png";

    private static String JPG_CONTENTTYPE = "image/jpg";

    private static String fileSuffix="";

    public DownLoadManager(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     *DownLoadManager getInstance
     */
    public static synchronized DownLoadManager getInstance(CallBack callBack) {
        if (downLoadManager == null) {
            downLoadManager = new DownLoadManager(callBack);
        }
        return downLoadManager;
    }

    public boolean  writeToDisk(Context context, ResponseBody body) throws
            IOException {
        Boolean isWrited = false;
        String type = body.contentType().toString();

        if (type.equals(APK_CONTENTTYPE)) {

            fileSuffix = ".apk";
        } else if (type.equals(PNG_CONTENTTYPE)) {
            fileSuffix = ".png";
        } else if (type.equals(JPG_CONTENTTYPE)) {
            fileSuffix = ".jpg";
        }
        final String name = System.currentTimeMillis() + fileSuffix;
        final String path = context.getExternalFilesDir(null) + File.separator + name;
        File futureStudioIconFile = new File(path);

        if (futureStudioIconFile.exists()) {
            futureStudioIconFile.delete();
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] fileReader = new byte[4096];

        final long fileSize = body.contentLength();
        long fileSizeDownloaded = 0;
        inputStream = body.byteStream();
        try {
            outputStream = new FileOutputStream(futureStudioIconFile);
            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }

                outputStream.write(fileReader, 0, read);

                fileSizeDownloaded += read;
                isWrited = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isWrited = false;
        }
        return isWrited;
    }

}
