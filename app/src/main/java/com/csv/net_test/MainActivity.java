package com.csv.net_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.csv.net_test.bean.TokenBean;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author CSV
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    /**
     * 用户ID,由数字组成,长度为15~25个字符
     */
    public String userId = "用户ID";
    private String ak = "4564";
    private String sk = "45648";

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化okhttp和retrofit
        NetWorkManager.getInstance().init();
        //
        /*Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("ak","69478724020200723095502");
        map.put("sk","6947872402020072309550205c4cc70987e44e88803896d0ee47730");
        String strEntity = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("Content-Type:application/json"), strEntity);*/
        NetWorkManager.getService()
                .getAccessToken(userId, ak, sk)
                .enqueue(new Callback<TokenBean>() {
                    @Override
                    public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                        String access_token = response.body().getAccess_token();
                        Log.i(TAG, "onResponse: 返回的Token参数" + access_token);
                    }

                    @Override
                    public void onFailure(Call<TokenBean> call, Throwable t) {
                        Log.e(TAG, "onFailure: 请求失败");
                    }
                });
    }


}