package com.zhl.scanlable.bean;

import java.io.Serializable;

public class AllKCBean implements Serializable {

    /**
     * msg : success
     * code : 0
     * data : 0
     */

    private String msg;
    private int code;
    private int data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
