package com.yangxuqiang.customview.base;

import android.app.Application;

/**
 * Created by yangxuqiang on 2018/2/1.
 */

public class CustomViewApplication extends Application {

    public static CustomViewApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
}
