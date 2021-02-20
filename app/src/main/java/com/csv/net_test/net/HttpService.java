package com.csv.net_test.net;

import com.csv.net_test.bean.DeviceBean;
import com.csv.net_test.bean.DeviceGatewayBean;
import com.csv.net_test.bean.DeviceInfoBean;
import com.csv.net_test.bean.TokenBean;
import com.csv.net_test.bean.XXBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
    String BASE_URL = "https://121.36.77.165";

    /**认证管理*/
    /**
     * 获取用户北向接口认证AccessToken
     *
     * @param userId
     * @param body
     * @return
     */
    @POST("/v1/{userId}/enterprises/access-token")
    Call<TokenBean> getAccessToken(@Path("userId") String userId, @Body RequestBody body);


    /**设备管理*/
    /**
     * 用户根据设备ID查询设备信息详情
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}")
    Call<DeviceInfoBean> getDeviceInfo(@Path("user_id") String userId, @Path("device_id") String deviceId,@Header("Access-Token") String header);

    /**
     * 用户查询设备列表
     *
     * @param user_id 用户ID
//     * @param params  Query参数
     * @return
     */
    @GET("/v1/{user_id}/devices")
    Call<DeviceBean> getDeviceList(@Path("user_id") String user_id/*, @FieldMap Map<String, Object> params*/, @Header("Access-Token") String header);

    /**
     * 查询设备网关详情 (仅支持国标)
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/gateway")
    Call<DeviceGatewayBean> getDeviceGateway(@Path("user_id") String userId, @Path("device_id") String deviceId);

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

    /**
     * 通过设备ID,通道ID查询通道详情
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}")
    Call<XXBean> queryChannelInfo(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**
     * 修改指定设备ID，通道ID的通道信息
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param channel_name
     * @return
     */
    @PUT("/v1/{user_id}/devices/{device_id}/channels/{channel_id}")
    Call<XXBean> alterChannelInfo(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @Field("channel_name") String channel_name);

    /**
     * 查询通道告警抓图策略
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/snap-policy")
    Call<XXBean> queryChannelSnapPolicy(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**
     * 批量设置通道告警抓图策略
     *
     * @param userId
     * @param map    设备通道告警抓图策略集合，个数限制100
     * @return
     */
    @PUT("/v1/{user_id}/devices/channels/snap-policy")
    Call<XXBean> setChannelsSnapPoliicy(@Path("user_id") String userId, @FieldMap Map<String, String> map);

    /**
     * 媒体播放管理
     */
    /**
     * 查询通道的云端录像列表
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param map        Query参数
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/cloud-records")
    Call<XXBean> queryChannelCloudRecords(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @QueryMap Map<String, Object> map);

    /**
     * 查询通道的云端录像回放地址
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param map        Query参数
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/cloud-records/playback-url")
    Call<XXBean> queryChannelCloudRecordsPlaybackUrl(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @QueryMap Map<String, String> map);

    /**
     * 查询前端的录像回放地址，仅支持国标设备
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param map
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/device-records/playback-url")
    Call<XXBean> queryDeviceRecordsPlaybackUrl(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @QueryMap Map<String, String> map);

    /**
     * 查询通道的前端录像列表，当前仅支持国标设备
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param map
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/device-records")
    Call<XXBean> queryChannelsRecords(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @QueryMap Map<String, String> map);

    /**
     * 批量获取设备实况播放地址，仅支持国标设备
     *
     * @param userId
     * @param map
     * @return
     */
    @POST("/v1/{user_id}/devices/channels/cloud-live/url")
    Call<XXBean> getChannelsCloudLiveUrl(@Path("user_id") String userId, @FieldMap Map<String, String> map);

    /**
     * 批量获取通道实况P2P地址
     *
     * @param userId
     * @param map    通道获取实况地址请求参数
     * @return
     */
    @POST("/v1/{user_id}/devices/channels/p2p-connect/live")
    Call<XXBean> getChannelsP2PLiveUrl(@Path("user_id") String userId, @FieldMap Map<String, String> map);

    /**
     * 批量获取通道录像回放P2P地址（仅支持好望协议）
     *
     * @param userId
     * @param map    请求体信息
     * @return
     */
    @POST("/v1/{user_id}/devices/channels/p2p-connect/playback")
    Call<XXBean> getChannelsP2PPlaybackUrl(@Path("user_id") String userId, @FieldMap Map<String, String> map);


    /**云台控制*/
    /**
     * 发送云台控制命令
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param control_token 设备操作授权token（有效期2分钟）
     * @param map           请求体信息
     * @return
     */
    @POST("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/ptz-twirl")
    Call<XXBean> sendPtzTwirl(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @Field("control_token") String control_token, @FieldMap Map<String, Object> map);

    /**
     * 发送云台聚焦和光圈控制命令
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @param control_token
     * @param map
     * @return
     */
    @POST("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/ptz-focus-iris")
    Call<XXBean> sendPtzFocusIris(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id, @Field("control_token") String control_token, @FieldMap Map<String, Object> map);

    /**
     * 获取云台控制权
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/control-token")
    Call<XXBean> getPtzControl(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**
     * 释放云台控制权
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @POST("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/control-token")
    Call<XXBean> releasePtzControl(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**录像配置管理*/
    /**
     * 批量为通道设置录像计划。通道与录像计划是一一对应的关系。一次最多设置100个通道。
     *
     * @param userId
     * @param map
     * @return
     */
    @PUT("/v1/{user_id}/devices/channels/record-plan")
    Call<XXBean> setChannelsRecordPlan(@Path("user_id") String userId, @FieldMap Map<String, Object> map);

    /**
     * 批量删除通道录像计划。需要先暂停录像，然后在删除通道录像计划
     *
     * @param userId
     * @param map
     * @return
     */
    @POST("/v1/{user_id}/devices/channels/record-plan")
    Call<XXBean> releaseChannelsRecordPlan(@Path("user_id") String userId, @FieldMap Map<String, String> map);

    /**
     * 查询通道的录像计划详细信息
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/record-plan")
    Call<XXBean> queryChannelsRecordPlan(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**
     * 批量设置通道存储时间策略。一次最多设置100个通道
     *
     * @param userId
     * @param map
     * @return
     */
    @PUT("/v1/{user_id}/devices/channels/time-policy")
    Call<XXBean> setChannelsStoreTimePolicy(@Path("user_id") String userId, @FieldMap Map<String, Object> map);

    /**
     * 查询通道的存储时间策略
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/time-policy")
    Call<XXBean> queryChannelStoreTimePolicy(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**
     * 批量设置通道录像参数。一次最多设置100个通道。
     *
     * @param userId
     * @param map
     * @return
     */
    @PUT("/v1/{user_id}/devices/channels/record-config")
    Call<XXBean> setChannelsRecordConfig(@Path("user_id") String userId, @FieldMap Map<String, String> map);

    /**
     * 查询通道录像参数
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/record-config")
    Call<XXBean> queryChannelRecordConfig(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);

    /**消息管理*/
    /**
     * 配置用户消息通知URL（增加或者更新用户消息通知URL）
     *
     * @param userId
     * @param callback_url
     * @param subscription_type
     * @return
     */
    @POST("/v1/{user_id}/messages/callback")
    Call<XXBean> setUserMessagesUrl(@Path("user_id") String userId, @Field("callback_url") String callback_url, @Field("subscription_type") String subscription_type);

    /**
     * 删除用户消息通知URL
     *
     * @param userId
     * @param subscription_type
     * @return
     */
    @DELETE("/v1/{user_id}/messages/callback")
    Call<XXBean> deleteUserMessagesUrl(@Path("user_id") String userId, @Query("subscription_type") String subscription_type);

    /**
     * 查询用户消息通知URL
     *
     * @param userId
     * @param subscription_type
     * @return
     */
    @GET("/v1/{user_id}/messages/callback")
    Call<XXBean> queryUserMessagesUrl(@Path("user_id") String userId, @Query("subscription_type") String subscription_type);

    /**服务开通关闭管理*/

    /**
     * 通道开通资源（管理、全量、动检、行业数据流）能力，支持批量
     *
     * @param userId
     * @param map
     * @return
     */
    @POST("/v1/{user_id}/devices/channels/resources")
    Call<XXBean> openDevicesChannelsResources(@Path("user_id") String userId, @FieldMap Map<String, Object> map);

    /**
     * 通道关闭资源（管理、全量、动检、行业数据流）能力，支持批量
     *
     * @param userId
     * @param map
     * @return
     */
    @DELETE("/v1/{user_id}/devices/channels/resources")
    Call<XXBean> closeDevicesChannelsResources(@Path("user_id") String userId, @FieldMap Map<String, Object> map);

    /**资源管理*/
    /**
     * 查询包周期和按需资源列表。购买套餐后会生成包周期资源，开通了按需计费时会生成按需资源。
     *
     * @param userId
     * @param map
     * @return
     */
    @GET("/v1/{user_id}/resources")
    Call<XXBean> queryUserResources(@Path("user_id") String userId, @QueryMap Map<String, Object> map);

    /**
     * 用户查询包周期或按需资源下的通道列表
     *
     * @param userId
     * @param resource_id
     * @param map
     * @return
     */
    @GET("/v1/{user_id}/resources/{resource_id}/channels")
    Call<XXBean> queryUserResourcesChannels(@Path("user_id") String userId, @Path("resource_id") String resource_id, @QueryMap Map<String, Integer> map);

    /**
     * 用户查询通道下的资源列表
     *
     * @param userId
     * @param device_id
     * @param channel_id
     * @return
     */
    @GET("/v1/{user_id}/devices/{device_id}/channels/{channel_id}/resources")
    Call<XXBean> queryUserChannelsResources(@Path("user_id") String userId, @Path("device_id") String device_id, @Path("channel_id") String channel_id);
}