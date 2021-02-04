package com.csv.net_test;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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
    public static final String PUB_KEY = "3082010a0282010100d52ff5dd432b3a05113ec1a7065fa5a80308810e4e181cf14f7598c8d553cccb7d5111fdcdb55f6ee84fc92cd594adc1245a9c4cd41cbe407a919c5b4d4a37a012f8834df8cfe947c490464602fc05c18960374198336ba1c2e56d2e984bdfb8683610520e417a1a9a5053a10457355cf45878612f04bb134e3d670cf96c6e598fd0c693308fe3d084a0a91692bbd9722f05852f507d910b782db4ab13a92a7df814ee4304dccdad1b766bb671b6f8de578b7f27e76a2000d8d9e6b429d4fef8ffaa4e8037e167a2ce48752f1435f08923ed7e2dafef52ff30fef9ab66fdb556a82b257443ba30a93fda7a0af20418aa0b45403a2f829ea6e4b8ddbb9987f1bf0203010001";


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

        /**
         * 不带证书校验的SSL
         */
        X509TrustManager trustManager = new X509TrustManager() {
            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                try {
                    if (chain == null) {
                        throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                    }

                    if (!(chain.length > 0)) {
                        throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                    }

                    if (!(null != authType && authType.equalsIgnoreCase("ECDHE_RSA"))) {
                        throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                    }

                    // Perform customary SSL/TLS checks
                    try {
                        TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                        tmf.init((KeyStore) null);
                        for (TrustManager trustManager : tmf.getTrustManagers()) {
                            ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                        }
                    } catch (Exception e) {
                        throw new CertificateException(e);
                    }
                    // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                    // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                    RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

                    String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                    // Pin it!
                    final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                    if (!expected) {
                        throw new CertificateException("checkServerTrusted: Expected public key: "
                                + PUB_KEY + ", got public key:" + encoded);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        SSLSocketFactory ssLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustManager},
                    new SecureRandom());
            ssLSocketFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
        }

        assert ssLSocketFactory != null;

        //初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                //设置拦截器,添加统一的请求头
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //以拦截到的请求为基础创建一个新的请求对象,然后插入Header
                        Request request = chain.request().newBuilder()
                                .addHeader("access_token", "d5464654151355456345")
                                .build();
                        //开始请求
                        return chain.proceed(request);
                    }
                })*/
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .sslSocketFactory(ssLSocketFactory,trustManager)
                .hostnameVerifier((hostname, session) -> {
                    if (TextUtils.isEmpty(hostname)) {
                        return false;
                    }
                    return true;
                })
                .build();

        //初始化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.BASE_URL)
                .client(client)
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
