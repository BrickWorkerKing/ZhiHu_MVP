package com.nn.zhihumvp.app;

import android.app.Application;

/**
 * @author LiuZongRui  16/11/16
 */

public class ProjectApplication extends Application {

    private static ProjectApplication mInstance;

    public synchronized static ProjectApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
