package com.csv.net_test.app;

import android.app.Application;

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
    }

    public static MyApp getInstance() {
        return myApp;
    }
}
