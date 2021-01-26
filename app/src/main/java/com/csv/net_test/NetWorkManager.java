package com.csv.net_test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author CSV
 * @describe: API初始化类
 * @date: 2021/1/26
 */
public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile HttpService mHttpService = null;

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init() {
        //初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //初始化retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HttpService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpService getService() {
        if (mHttpService == null) {
            synchronized (Request.class) {
                mHttpService = retrofit.create(HttpService.class);
            }
        }
        return mHttpService;
    }
}
