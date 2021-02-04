package com.csv.net_test.app;

import android.app.Application;

import com.csv.net_test.NetWorkManager;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/28
 */
public class MyApp extends Application {
    public static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        //初始化okhttp和retrofit
        NetWorkManager.getInstance().init();
    }

    public static MyApp getInstance() {
        return myApp;
    }
}
