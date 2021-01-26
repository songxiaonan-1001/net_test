package com.csv.net_test.bean;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/26
 */
public class DeviceInfoBean {
    /**
     * {
     *   "device_id" : "34020000002xxxxxxxx1",
     *   "device_name" : "测试设备",
     *   "device_state" : "ONLINE",
     *   "access_protocol" : "HOLO",
     *   "device_type" : "IPC",
     *   "device_system_state" : "NORMAL",
     *   "description" : "test",
     *   "model" : "JVS-N71-HD",
     *   "manufacture" : "JOVISION",
     *   "channel_total" : 10,
     *   "stream_total" : 2,
     *   "firmware" : "V1.0",
     *   "ability" : "talk,capture",
     *   "create_time" : "2020-06-26 20:42:16",
     *   "update_time" : "2020-06-26 20:42:16",
     *   "protocol_version" : "2.0.0",
     *   "mac" : "xxx",
     *   "device_ip" : "xxx.xxx.xxx.xxx",
     *   "device_port" : "8090"
     * }
     */

    private String device_id;
    private String device_name;
    private String device_state;
    private String access_protocol;
    private String device_type;
    private String device_system_state;
    private String description;
    private String model;
    private String manufacture;
    private String channel_total;
    private String stream_total;
    private String firmware;
    private String ability;
    private String create_time;
    private String update_time;
    private String protocol_version;
    private String mac;
    private String device_ip;
    private String device_port;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_state() {
        return device_state;
    }

    public void setDevice_state(String device_state) {
        this.device_state = device_state;
    }

    public String getAccess_protocol() {
        return access_protocol;
    }

    public void setAccess_protocol(String access_protocol) {
        this.access_protocol = access_protocol;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_system_state() {
        return device_system_state;
    }

    public void setDevice_system_state(String device_system_state) {
        this.device_system_state = device_system_state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getChannel_total() {
        return channel_total;
    }

    public void setChannel_total(String channel_total) {
        this.channel_total = channel_total;
    }

    public String getStream_total() {
        return stream_total;
    }

    public void setStream_total(String stream_total) {
        this.stream_total = stream_total;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getProtocol_version() {
        return protocol_version;
    }

    public void setProtocol_version(String protocol_version) {
        this.protocol_version = protocol_version;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public String getDevice_port() {
        return device_port;
    }

    public void setDevice_port(String device_port) {
        this.device_port = device_port;
    }

    @Override
    public String toString() {
        return "DeviceInfoBean{" +
                "device_id='" + device_id + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_state='" + device_state + '\'' +
                ", access_protocol='" + access_protocol + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_system_state='" + device_system_state + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", channel_total='" + channel_total + '\'' +
                ", stream_total='" + stream_total + '\'' +
                ", firmware='" + firmware + '\'' +
                ", ability='" + ability + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", protocol_version='" + protocol_version + '\'' +
                ", mac='" + mac + '\'' +
                ", device_ip='" + device_ip + '\'' +
                ", device_port='" + device_port + '\'' +
                '}';
    }
}
