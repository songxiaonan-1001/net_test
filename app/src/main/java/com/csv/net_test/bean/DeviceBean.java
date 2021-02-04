package com.csv.net_test.bean;

import java.util.List;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/28
 */
public class DeviceBean {
    /**
     * total : 2
     * devices : [{"device_id":"34020000002xxxxxxxx1","device_name":"测试设备","device_state":"ONLINE","device_system_state":"NORMAL","device_type":"IPC","manufacture":"jovision","device_ability":"ptz,talk","access_protocol":"HOLO","description":"这是一个测试设备","create_time":"2020-06-26 20:42:16","update_time":"2020-06-26 20:42:16","channel_total":10},{"device_id":"34020000002xxxxxxxx2","device_name":"测试设备1","device_state":"ONLINE","device_system_state":"NORMAL","device_type":"IPC","manufacture":"jovision","device_ability":"ptz,talk","access_protocol":"HOLO","description":"这是一个测试设备2","create_time":"2020-06-26 20:42:16","update_time":"2020-06-26 20:42:16","channel_total":10}]
     */

    private int total;
    private List<DevicesBean> devices;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class DevicesBean {
        /**
         * device_id : 34020000002xxxxxxxx1
         * device_name : 测试设备
         * device_state : ONLINE
         * device_system_state : NORMAL
         * device_type : IPC
         * manufacture : jovision
         * device_ability : ptz,talk
         * access_protocol : HOLO
         * description : 这是一个测试设备
         * create_time : 2020-06-26 20:42:16
         * update_time : 2020-06-26 20:42:16
         * channel_total : 10
         */

        private String device_id;
        private String device_name;
        private String device_state;
        private String device_system_state;
        private String device_type;
        private String manufacture;
        private String device_ability;
        private String access_protocol;
        private String description;
        private String create_time;
        private String update_time;
        private int channel_total;

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

        public String getDevice_system_state() {
            return device_system_state;
        }

        public void setDevice_system_state(String device_system_state) {
            this.device_system_state = device_system_state;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getManufacture() {
            return manufacture;
        }

        public void setManufacture(String manufacture) {
            this.manufacture = manufacture;
        }

        public String getDevice_ability() {
            return device_ability;
        }

        public void setDevice_ability(String device_ability) {
            this.device_ability = device_ability;
        }

        public String getAccess_protocol() {
            return access_protocol;
        }

        public void setAccess_protocol(String access_protocol) {
            this.access_protocol = access_protocol;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getChannel_total() {
            return channel_total;
        }

        public void setChannel_total(int channel_total) {
            this.channel_total = channel_total;
        }

        @Override
        public String toString() {
            return "DevicesBean{" +
                    "device_id='" + device_id + '\'' +
                    ", device_name='" + device_name + '\'' +
                    ", device_state='" + device_state + '\'' +
                    ", device_system_state='" + device_system_state + '\'' +
                    ", device_type='" + device_type + '\'' +
                    ", manufacture='" + manufacture + '\'' +
                    ", device_ability='" + device_ability + '\'' +
                    ", access_protocol='" + access_protocol + '\'' +
                    ", description='" + description + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", channel_total=" + channel_total +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "total=" + total +
                ", devices=" + devices +
                '}';
    }
}
