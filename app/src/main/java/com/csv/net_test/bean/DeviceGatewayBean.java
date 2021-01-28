package com.csv.net_test.bean;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/28
 */
public class DeviceGatewayBean {
    /**
     * device_id : 4400000000xxx0001
     * device_username : admin
     * sip_server_id : 34020000002000000001
     * sip_server_domain : 3402000000
     * sip_server_host : 123.123.123.123
     * sip_server_port : 5060
     */

    private String device_id;
    private String device_username;
    private String sip_server_id;
    private String sip_server_domain;
    private String sip_server_host;
    private int sip_server_port;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_username() {
        return device_username;
    }

    public void setDevice_username(String device_username) {
        this.device_username = device_username;
    }

    public String getSip_server_id() {
        return sip_server_id;
    }

    public void setSip_server_id(String sip_server_id) {
        this.sip_server_id = sip_server_id;
    }

    public String getSip_server_domain() {
        return sip_server_domain;
    }

    public void setSip_server_domain(String sip_server_domain) {
        this.sip_server_domain = sip_server_domain;
    }

    public String getSip_server_host() {
        return sip_server_host;
    }

    public void setSip_server_host(String sip_server_host) {
        this.sip_server_host = sip_server_host;
    }

    public int getSip_server_port() {
        return sip_server_port;
    }

    public void setSip_server_port(int sip_server_port) {
        this.sip_server_port = sip_server_port;
    }
}
