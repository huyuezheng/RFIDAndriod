package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dq on 2018/8/15 0015.
 */

public class AssetsmanagementBean implements Serializable{


    /**
     * msg : success
     * code : 0
     * data : [{"goodsId":"1120940982599409665","goodsName":"打印机","goodsTypeId":"1120879959783661570","goodsSupplier":"","goodsResalePrice":null,"goodsQrcode":100003,"goodsSpe":"","goodsUnit":"","createTime":1556088131000,"createPeople":"","updateTime":1556088155000,"updatePeople":"","goodsRfid":"E2000016200B025912209E27","delFlag":"0"}]
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
         * goodsId : 1120940982599409665
         * goodsName : 打印机
         * goodsTypeId : 1120879959783661570
         * goodsSupplier :
         * goodsResalePrice : null
         * goodsQrcode : 100003
         * goodsSpe :
         * goodsUnit :
         * createTime : 1556088131000
         * createPeople :
         * updateTime : 1556088155000
         * updatePeople :
         * goodsRfid : E2000016200B025912209E27
         * delFlag : 0
         */

        private String goodsId;
        private String goodsName;
        private String goodsTypeId;
        private String goodsSupplier;
        private Object goodsResalePrice;
        private String goodsQrcode;
        private String goodsSpe;
        private String goodsUnit;
        private long createTime;
        private String createPeople;
        private long updateTime;
        private String updatePeople;
        private String goodsRfid;
        private String delFlag;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsTypeId() {
            return goodsTypeId;
        }

        public void setGoodsTypeId(String goodsTypeId) {
            this.goodsTypeId = goodsTypeId;
        }

        public String getGoodsSupplier() {
            return goodsSupplier;
        }

        public void setGoodsSupplier(String goodsSupplier) {
            this.goodsSupplier = goodsSupplier;
        }

        public Object getGoodsResalePrice() {
            return goodsResalePrice;
        }

        public void setGoodsResalePrice(Object goodsResalePrice) {
            this.goodsResalePrice = goodsResalePrice;
        }

        public String getGoodsQrcode() {
            return goodsQrcode;
        }

        public void setGoodsQrcode(String goodsQrcode) {
            this.goodsQrcode = goodsQrcode;
        }

        public String getGoodsSpe() {
            return goodsSpe;
        }

        public void setGoodsSpe(String goodsSpe) {
            this.goodsSpe = goodsSpe;
        }

        public String getGoodsUnit() {
            return goodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            this.goodsUnit = goodsUnit;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreatePeople() {
            return createPeople;
        }

        public void setCreatePeople(String createPeople) {
            this.createPeople = createPeople;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdatePeople() {
            return updatePeople;
        }

        public void setUpdatePeople(String updatePeople) {
            this.updatePeople = updatePeople;
        }

        public String getGoodsRfid() {
            return goodsRfid;
        }

        public void setGoodsRfid(String goodsRfid) {
            this.goodsRfid = goodsRfid;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
