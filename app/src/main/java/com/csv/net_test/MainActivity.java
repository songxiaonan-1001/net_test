package com.csv.net_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.csv.net_test.bean.DeviceBean;
import com.csv.net_test.bean.TokenBean;
import com.csv.net_test.bean.XXBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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
    private String userId = "174367170720210126145700";
    private String ak = "178661947120210128195511";
    private String sk = "178661947120210128195511693dc92939ac43a9ac5ee834bfb701e7";

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

        //获取用户北向接口认证Token
        NetWorkManager.getService()
                .getAccessToken(userId, ak, sk)
                .equals(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String access_token = response.body().toString();
                        Log.i(TAG, "onResponse: 返回的Token参数" + access_token);
                        Toast.makeText(MainActivity.this, access_token, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure: 获取Token失败");
                        Toast.makeText(MainActivity.this, "获取Token失败,请检查相关参数", Toast.LENGTH_SHORT).show();
                    }
                });


        HashMap<String, Object> deviceMap = new HashMap<>();
        deviceMap.put("access_protocol", "HOLO,GB28181");//接入协议
        deviceMap.put("device_name", "");//设备名称(模糊查询字段)
        deviceMap.put("device_state", "");//设备在线状态(筛选查询字段)
        deviceMap.put("device_system_state", "");//设备系统状态
        deviceMap.put("device_type", "");//设备类型(筛选查询字段)
        deviceMap.put("direction", "");//排序方向(默认降序)
        deviceMap.put("limit", "");//限制条数(1-1000,默认10)
        deviceMap.put("offset", "");//偏移量页数(0-2147483646,默认0)
        deviceMap.put("sort_by", "");//排序字段(默认按创建时间)


        //查询设备列表
       /* NetWorkManager.getService()
                .getDeviceList(userId, deviceMap)
                .enqueue(new Callback<DeviceBean>() {
                    @Override
                    public void onResponse(Call<DeviceBean> call, Response<DeviceBean> response) {
                        String string = response.body().toString();
                        Log.i(TAG, "onResponse: " + string);
                    }

                    @Override
                    public void onFailure(Call<DeviceBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: 查询设备列表失败");
                        Toast.makeText(MainActivity.this, "查询设备列表失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });
*/

    }


}