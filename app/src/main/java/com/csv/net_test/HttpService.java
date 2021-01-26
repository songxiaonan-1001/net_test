package com.csv.net_test;

import com.csv.net_test.bean.DeviceInfoBean;
import com.csv.net_test.bean.TokenBean;
import com.csv.net_test.bean.XXBean;

import java.util.Map;
import java.util.Observable;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/26
 */
public interface HttpService {
    String BASE_URL = "XXXXXXXXXXXXXX";

    /**认证管理*/
    /**
     * 获取用户北向接口认证AccessToken
     *
     * @param userId    用户ID
     * @param ak        ak
     * @param sk        sk
     * @return
     */
    @FormUrlEncoded
    @POST("/v1/{user_id}/enterprises/access-token")
    Call<TokenBean> getAccessToken(@Path("user_id") String userId, @Field("ak") String ak, @Field("sk") String sk);


    /**设备管理*/
    /**
     * 用户根据设备ID查询设备信息详情
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}")
    Call<DeviceInfoBean> getDeviceInfo(@Path("user_id") String userId, @Path("device_id") String deviceId);

    /**
     * 用户查询设备列表
     *
     * @param user_id 用户ID
     * @param params  Query参数
     * @return
     */
    Call<XXBean> getDeviceList(@Path("user_id") String user_id, @QueryMap Map<String, String> params);

    /**
     * 查询设备网关详情 (仅支持国标)
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/gateway")
    Call<XXBean> getDeviceGateway(@Path("user_id") String userId, @Path("device_id") String deviceId);

    /**
     * 用户提交修改的设备信息，设备处于上线状态才可以修改设备名称
     *
     * @param userId      用户ID
     * @param deviceId    设备ID
     * @param device_name 设备名称
     * @param description 设备描述
     * @return
     */
    @Multipart
    @PUT("/v1/{user_id}/devices/{device_id}")
    Call<XXBean> alterDeviceInfo(@Path("user_id") String userId, @Path("device_id") String deviceId, @Part("device_name") RequestBody device_name, @Part("description") RequestBody description);

    /**
     * 根据设备信息修改国标设备账号
     *
     * @param userId          用户ID
     * @param deviceId        设备ID
     * @param device_username 设备用户名(国标)
     * @param device_password 设备鉴权密码
     * @return
     */
    @Multipart
    @PUT("/v1/{user_id}/devices/{device_id}/gb-account")
    Call<XXBean> alterDeviceAccount(@Path("user_id") String userId, @Path("device_id") String deviceId, @Part("device_username") RequestBody device_username, @Part("device_password") RequestBody device_password);

    /**
     * 添加好望协议设备
     *
     * @param userId            用户ID
     * @param device_id         设备ID
     * @param verification_code 验证码
     * @param description       设备描述
     * @return
     */
    @Multipart
    @POST("/v1/{user_id}/devices/holosens")
    Call<XXBean> addDeviceHolo(@Path("user_id") String userId, @Part("device_id") RequestBody device_id, @Part("verification_code") RequestBody verification_code, @Part("description") RequestBody description);

    /**
     * 批量添加设备，仅支持GB28181协议，最大支持批量新增100个设备
     *
     * @param userId  用户ID
     * @param devices 设备参数列表
     * @return
     */
    @Multipart
    @POST("/v1/{user_id}/devices/gb/batch-add")
    Call<XXBean> addDevicesBatchGB(@Path("user_id") String userId, @Part("devices") RequestBody devices);

    /**
     * 批量删除设备,最大支持批量删除100个设备
     *
     * @param userId       用户ID
     * @param deviceIdList 设备ID集合
     * @return
     */
    @Multipart
    @POST("/v1/{user_id}/devices/batch-delete")
    Call<XXBean> deleteDevicesBatch(@Path("user_id") String userId, @Part("deviceIdList") RequestBody deviceIdList);


    /**视频通道管理*/
    /**
     * 查询通道列表，支持多条件查询
     *
     * @param userId 用户ID
     * @param map    Query参数集合
     * @return
     */
    @GET("/v1/{user_id}/channels")
    Call<XXBean> queryChannels(@Path("user_id") String userId, @QueryMap Map<String, String> map);


    /**媒体播放管理*/


    /**云台控制*/


    /**录像配置管理*/


    /**消息管理*/


    /**服务开通关闭管理*/


    /**资源管理*/


}