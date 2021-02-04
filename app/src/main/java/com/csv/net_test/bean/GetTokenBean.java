package com.csv.net_test.bean;

/**
 * @author CSV
 * @describe:
 * @date: 2021/1/29
 */
public class GetTokenBean {
    /**
     * ak : 69478724020200723095502
     * sk : 6947872402020072309550205c4cc70987e44e88803896d0ee47730
     */

    private String ak;
    private String sk;

    public String getAk() {
        return ak;
    }

    public GetTokenBean(String ak, String sk) {
        this.ak = ak;
        this.sk = sk;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }
}
