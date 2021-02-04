package com.csv.net_test;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.csv.net_test.utils.SpUtil;

import java.util.HashMap;

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
    private VideoView videoViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*HashMap<String, Object> deviceMap = new HashMap<>();
        deviceMap.put("access_protocol", "HOLO,GB28181");//接入协议
        deviceMap.put("device_name", "");//设备名称(模糊查询字段)
        deviceMap.put("device_state", "");//设备在线状态(筛选查询字段)
        deviceMap.put("device_system_state", "");//设备系统状态
        deviceMap.put("device_type", "");//设备类型(筛选查询字段)
        deviceMap.put("direction", "");//排序方向(默认降序)
        deviceMap.put("limit", "");//限制条数(1-1000,默认10)
        deviceMap.put("offset", "");//偏移量页数(0-2147483646,默认0)
        deviceMap.put("sort_by", "");//排序字段(默认按创建时间)*/


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

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();

       /* SpUtil spUtil = new SpUtil(this, "access_token");
        String access_token = spUtil.getString("access_token", "");
        Toast.makeText(this, "access_token值为xxx,请获取", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(access_token)) {
            Log.i(TAG, "onCreate: access_token值为null,请获取" + access_token);
            //获取用户北向接口认证Token
            NetWorkManager.getService()
                    .getAccessToken(userId, ak, sk)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String access_token = response.body().toString();
                            Log.i(TAG, "onResponse: 返回的Token参数" + access_token);
                            spUtil.clear();
                            spUtil.putString("access_token", access_token);
                            Toast.makeText(MainActivity.this, access_token, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e(TAG, "onFailure: 获取Token失败");
                            Toast.makeText(MainActivity.this, "获取Token失败,请检查相关参数", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Log.i(TAG, "onStart: access_token值不为null,其值为:" + access_token);
        }*/
    }

    private void initView() {
        String url = "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp";
//        String url = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
//        String url = "jvmp://121.36.92.161:7070/live/2102412464WLL4007607?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsX2lkIjowLCJkZXZpY2VfaWQiOiIyMTAyNDEyNDY0V0xMNDAwNzYwNyIsImV4cGlyZV90aW1lIjoxNjEyMTE1MjkxLCJzdHJlYW1fdHlwZSI6MCwidXNlcl9pZCI6IjE3NDM2NzE3MDcyMDIxMDEyNjE0NTcwMCIsInVzZXJfdHlwZSI6IkVOVEVSUFJJU0UifQ.8DFQJ9WifjZDRV2eeTDQRDfA5QF7lZadRGsbxyYdCco";
        videoViewMain = findViewById(R.id.videoView_main);
        videoViewMain.setVideoPath(url);
        videoViewMain.requestFocus();
        videoViewMain.start();
    }
}