package com.csv.net_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csv.net_test.bean.DeviceBean;
import com.csv.net_test.bean.DeviceGatewayBean;
import com.csv.net_test.bean.DeviceInfoBean;
import com.csv.net_test.bean.XXBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author CSV
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    /**
     * 用户ID,由数字组成,长度为15~25个字符
     */
    private String userId = "174367170720210126145700";
    private String ak = "178661947120210128195511";
    private String sk = "178661947120210128195511693dc92939ac43a9ac5ee834bfb701e7";
    private Button getToken;
    private Button getDeviceList;
    private Button getDeviceInfo;
    private Button getDeviceGateway;
    private Button alterDeviceInfo;
    private Button alterDeviceAccount;
    private Button addDeviceHolo;
    private Button addDevicesBatchGB;
    private Button deleteDevicesBatch;
    private TextView tvTest;
    private HashMap<String, Object> deviceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //初始化okhttp和retrofit
        NetWorkManager.getInstance().init();

        initView();

        deviceMap = new HashMap<>(9);
        deviceMap.put("access_protocol", "HOLO,GB28181");//接入协议
        deviceMap.put("device_name", "");//设备名称(模糊查询字段)
        deviceMap.put("device_state", "");//设备在线状态(筛选查询字段)
        deviceMap.put("device_system_state", "");//设备系统状态
        deviceMap.put("device_type", "");//设备类型(筛选查询字段)
        deviceMap.put("direction", "");//排序方向(默认降序)
        deviceMap.put("limit", "");//限制条数(1-1000,默认10)
        deviceMap.put("offset", "");//偏移量页数(0-2147483646,默认0)
        deviceMap.put("sort_by", "");//排序字段(默认按创建时间)


    }

    private void initView() {
        getToken = findViewById(R.id.getToken);
        getDeviceList = findViewById(R.id.getDeviceList);
        getDeviceInfo = findViewById(R.id.getDeviceInfo);
        getDeviceGateway = findViewById(R.id.getDeviceGateway);
        alterDeviceInfo = findViewById(R.id.alterDeviceInfo);
        alterDeviceAccount = findViewById(R.id.alterDeviceAccount);
        addDeviceHolo = findViewById(R.id.addDeviceHolo);
        addDevicesBatchGB = findViewById(R.id.addDevicesBatchGB);
        deleteDevicesBatch = findViewById(R.id.deleteDevicesBatch);
        tvTest = findViewById(R.id.tv_test);

        getToken.setOnClickListener(this);
        getDeviceList.setOnClickListener(this);
        getDeviceInfo.setOnClickListener(this);
        getDeviceGateway.setOnClickListener(this);
        alterDeviceInfo.setOnClickListener(this);
        alterDeviceAccount.setOnClickListener(this);
        addDeviceHolo.setOnClickListener(this);
        addDevicesBatchGB.setOnClickListener(this);
        deleteDevicesBatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getToken:
                //获取用户北向接口认证Token
                NetWorkManager.getService()
                        .getAccessToken(userId, ak, sk)
                        .equals(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String access_token = response.body().toString();
                                Log.i(TAG, "onResponse: 返回的Token参数" + access_token);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.e(TAG, "onFailure: 获取Token失败");
                                Toast.makeText(TestActivity.this, "获取Token失败,请检查相关参数", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.getDeviceList:
                //查询设备列表
                NetWorkManager.getService()
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
                                Toast.makeText(TestActivity.this, "查询设备列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.getDeviceInfo:
                //查询设备信息
                NetWorkManager.getService()
                        .getDeviceInfo(userId, new DeviceBean().getDevices().get(0).getDevice_id())
                        .enqueue(new Callback<DeviceInfoBean>() {
                            @Override
                            public void onResponse(Call<DeviceInfoBean> call, Response<DeviceInfoBean> response) {
                                String string = response.body().toString();
                                Log.i(TAG, "onResponse: 获取到的设备信息" + string);
                            }

                            @Override
                            public void onFailure(Call<DeviceInfoBean> call, Throwable t) {
                                Log.i(TAG, "onFailure: 获取设备信息失败,请检查");
                            }
                        });
                break;
            case R.id.getDeviceGateway:
                //查询设备网关
                NetWorkManager.getService()
                        .getDeviceGateway(userId,new DeviceBean().getDevices().get(0).getDevice_id())
                        .enqueue(new Callback<DeviceGatewayBean>() {
                            @Override
                            public void onResponse(Call<DeviceGatewayBean> call, Response<DeviceGatewayBean> response) {
                                String string = response.body().toString();
                                Log.i(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<DeviceGatewayBean> call, Throwable t) {

                            }
                        });
                break;
            case R.id.alterDeviceInfo:
                //修改设备信息
                break;
            case R.id.alterDeviceAccount:
                //修改国标设备账号
                break;
            case R.id.addDeviceHolo:
                //添加设备(仅支持好望协议)
                break;
            case R.id.addDevicesBatchGB:
                //批量添加设备(仅支持GB28181协议)
                break;
            default:
                break;
        }
    }
}