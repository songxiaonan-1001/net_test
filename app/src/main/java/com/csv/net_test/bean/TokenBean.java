package com.csv.net_test.bean;

/**
 * @author CSV
 * @describe: Token实体类
 * @date: 2021/1/26
 */
public class TokenBean {

    /**
     * {
     * "access_token" : "6947872402020072309550205c4cc70987e44e88803896d0ee47730"
     * }
     */

    public String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}
