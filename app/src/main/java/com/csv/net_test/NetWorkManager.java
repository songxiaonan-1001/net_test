package com.csv.net_test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                //设置拦截器,添加统一的请求头
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //以拦截到的请求为基础创建一个新的请求对象,然后插入Header
                        Request request = chain.request().newBuilder()
                                .addHeader("access_token", "d5464654151355456345")
                                .build();
                        //开始请求
                        return chain.proceed(request);
                    }
                })
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
