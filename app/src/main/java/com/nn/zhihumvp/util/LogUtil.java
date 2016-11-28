package com.nn.zhihumvp.util;

import android.util.Log;

import com.nn.zhihumvp.BuildConfig;


/**
 * 日志类
 * Created by 右右 on 15/7/28.
 */
public class LogUtil {

    public static final String LOG_TAG = "lzrtest";
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static  void d(String log) {
        if (DEBUG)
            Log.d(LOG_TAG, log);
    }

    public static  void d(String tag, String log) {
        if (DEBUG)
            Log.d(tag, "" + log);
    }

    public static  void e(String log) {
        if (DEBUG)
            Log.e(LOG_TAG, "" + log);
    }

    public static  void e(String tag, String log) {
        if (DEBUG)
            Log.e(tag, "" + log);
    }

    public static  void i(String log) {
        if (DEBUG)
            Log.i(LOG_TAG, log);
    }

    public static  void i(String tag, String log) {
        if (DEBUG)
            Log.i(tag, log);
    }

    public static  void v(String log) {
        if (DEBUG)
            Log.v(LOG_TAG, log);
    }

    public static  void v(String tag, String log) {
        if (DEBUG)
            Log.v(tag, "" + log);
    }

    public static  void w(String log) {
        if (DEBUG)
            Log.w(LOG_TAG, log);
    }

    public static  void w(String tag, String log) {
        if (DEBUG)
            Log.w(tag, "" + log);
    }
}
