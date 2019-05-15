package com.zhl.scanlable.bean;

import java.io.Serializable;

public class JiHuoBean implements Serializable {

    /**
     * msg : success
     * code : 0
     * data : {"goodsId":"1120947535880527874","goodsName":"打印机2","goodsTypeId":"","goodsSupplier":"","goodsResalePrice":null,"goodsQrcode":100006,"goodsSpe":"","goodsUnit":"","createTime":1556089693000,"createPeople":"","updateTime":1556093562000,"updatePeople":"","goodsRfid":"E2000016200B025912209E27","delFlag":"0"}
     */

    private String msg;
    private int code;
    private AssetsmanagementBean.DataBean data;

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

    public AssetsmanagementBean.DataBean getData() {
        return data;
    }

    public void setData(AssetsmanagementBean.DataBean data) {
        this.data = data;
    }
}
