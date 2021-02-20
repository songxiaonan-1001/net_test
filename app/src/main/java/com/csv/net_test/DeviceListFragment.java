package com.csv.net_test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csv.net_test.base.BaseFragment;
import com.csv.net_test.bean.DeviceResponseBean;
import com.csv.net_test.bean.TokenBean;
import com.csv.net_test.consts.SharedPreferenceKey;
import com.csv.net_test.net.NetWorkManager;
import com.csv.net_test.utils.SpUtil;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author CSV
 * @describe:
 * @date: 2021/2/20
 */
public class DeviceListFragment extends BaseFragment {

    private static final String TAG = "DeviceListFragment";
    private Button mGetToken;
    private RecyclerView recyclerView;
    private List<DeviceResponseBean.DevicesBean> deviceLists = new ArrayList<>();
    private DeviceListAdapter adapter;
    private String userId = "174367170720210126145700";
    private String ak = "178661947120210128195511";
    private String sk = "178661947120210128195511693dc92939ac43a9ac5ee834bfb701e7";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_list, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DeviceListAdapter(R.layout.item_device_list, deviceLists);

        mGetToken = view.findViewById(R.id.get_token);
        mGetToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken();
            }
        });

        return view;
    }

    /**
     * 加载Token
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
                        Toast.makeText(getActivity(), "获取Token失败,请检查相关参数", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
