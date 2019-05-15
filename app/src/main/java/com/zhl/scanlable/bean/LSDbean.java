package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

public class LSDbean implements Serializable {

    /**
     * msg : success
     * code : 0
     * data : [{"saleShopId":"1127506700950183937","saleShopName":"容景文创店","saleShopLinkMan":"孟繁光","saleShopLinkManPhone":"18867425614","saleShopAddress":"容景宾馆内","delFlag":0}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * saleShopId : 1127506700950183937
         * saleShopName : 容景文创店
         * saleShopLinkMan : 孟繁光
         * saleShopLinkManPhone : 18867425614
         * saleShopAddress : 容景宾馆内
         * delFlag : 0
         */

        private String saleShopId;
        private String saleShopName;
        private String saleShopLinkMan;
        private String saleShopLinkManPhone;
        private String saleShopAddress;
        private int delFlag;

        public String getSaleShopId() {
            return saleShopId;
        }

        public void setSaleShopId(String saleShopId) {
            this.saleShopId = saleShopId;
        }

        public String getSaleShopName() {
            return saleShopName;
        }

        public void setSaleShopName(String saleShopName) {
            this.saleShopName = saleShopName;
        }

        public String getSaleShopLinkMan() {
            return saleShopLinkMan;
        }

        public void setSaleShopLinkMan(String saleShopLinkMan) {
            this.saleShopLinkMan = saleShopLinkMan;
        }

        public String getSaleShopLinkManPhone() {
            return saleShopLinkManPhone;
        }

        public void setSaleShopLinkManPhone(String saleShopLinkManPhone) {
            this.saleShopLinkManPhone = saleShopLinkManPhone;
        }

        public String getSaleShopAddress() {
            return saleShopAddress;
        }

        public void setSaleShopAddress(String saleShopAddress) {
            this.saleShopAddress = saleShopAddress;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
