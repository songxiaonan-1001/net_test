package com.csv.net_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csv.net_test.bean.DeviceBean;
import com.csv.net_test.bean.DeviceInfoBean;
import com.csv.net_test.bean.TokenBean;
import com.csv.net_test.consts.SharedPreferenceKey;
import com.csv.net_test.net.NetWorkManager;
import com.csv.net_test.utils.SpUtil;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private Button gotoPlay;
    private TextView tvTest;
    private HashMap<String, Object> deviceMap;
    private SpUtil spUtil;
    private String token;

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

        token = SpUtil.getString(SharedPreferenceKey.LoginKey.TOKEN, "");

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
        gotoPlay = findViewById(R.id.gotoPlay);
        tvTest = findViewById(R.id.tv_test);
        tvTest.setText(SpUtil.getString("token", "暂无信息!"));

        getToken.setOnClickListener(this);
        getDeviceList.setOnClickListener(this);
        getDeviceInfo.setOnClickListener(this);
        getDeviceGateway.setOnClickListener(this);
        alterDeviceInfo.setOnClickListener(this);
        alterDeviceAccount.setOnClickListener(this);
        addDeviceHolo.setOnClickListener(this);
        addDevicesBatchGB.setOnClickListener(this);
        deleteDevicesBatch.setOnClickListener(this);
        gotoPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getToken:
                getToken();
                break;
            case R.id.getDeviceList:
                //查询设备列表
                getDeviceList();
                break;
            case R.id.getDeviceInfo:
                //查询设备信息
                getDeviceInfo();
                break;
            case R.id.getDeviceGateway:
                //查询设备网关

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
            case R.id.gotoPlay:
                startActivity(new Intent(TestActivity.this, PlayActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 获取Token
     */
    private void getToken() {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("ak", ak);
        params.put("sk", sk);
        String json = new GsonBuilder()
                .disableHtmlEscaping()
                .create()
                .toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);


        //获取用户北向接口认证Token
        NetWorkManager.getService()
                .getAccessToken(userId, body)
                .enqueue(new Callback<TokenBean>() {
                    @Override
                    public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                        String token = response.body().getAccess_token();
                        Log.i(TAG, "onResponse:获取到的token " + token);
                        SpUtil.putString(SharedPreferenceKey.LoginKey.TOKEN, token);
                    }

                    @Override
                    public void onFailure(Call<TokenBean> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                        Log.e(TAG, "onFailure: 获取Token失败");
                        Toast.makeText(TestActivity.this, "获取Token失败,请检查相关参数", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 获取设备列表(设备id)
     */
    private void getDeviceList() {
        NetWorkManager.getService()
                .getDeviceList(userId, token)
                .enqueue(new Callback<DeviceBean>() {
                    @Override
                    public void onResponse(Call<DeviceBean> call, Response<DeviceBean> response) {
//                        List<DeviceBean.DevicesBean> devices = response.body().getDevices();
//                        Log.i(TAG, "onResponse: "+devices.toString());
//                        DeviceBean deviceBean = new Gson().fromJson(response.body().getDevices().toString(), DeviceBean.class);
//                        int total = deviceBean.getTotal();
//                        response.body().getDevices().toString()

//                        String string = response.body().getDevices().toString();
                        for (int i = 0; i < response.body().getTotal(); i++) {
                            //遍历出设备id
                            String device_id = response.body().getDevices().get(i).getDevice_id();
                            SpUtil.putString("device_id" + i, device_id);
                            Log.i(TAG, "onResponse: " + device_id);
                        }
                    }

                    @Override
                    public void onFailure(Call<DeviceBean> call, Throwable t) {
                        //如果失败就从新获取Token
                        getToken();
                    }
                });
    }

    private void getDeviceInfo() {
        NetWorkManager.getService()
                .getDeviceInfo(userId, SpUtil.getString("device_id" + 0, ""), token)
                .enqueue(new Callback<DeviceInfoBean>() {
                    @Override
                    public void onResponse(Call<DeviceInfoBean> call, Response<DeviceInfoBean> response) {

                    }

                    @Override
                    public void onFailure(Call<DeviceInfoBean> call, Throwable t) {

                    }
                });
    }
}