package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

public class TypeBean implements Serializable {

    /**
     * msg : success
     * code : 0
     * data : [{"goodsTypeId":"1120879959783661570","goodsTypeName":"电脑主机","createPeople":"","createTime":null,"updatePeople":"","updateTime":null,"delFlag":"0"},{"goodsTypeId":"1120880014330585090","goodsTypeName":"显示器","createPeople":"","createTime":null,"updatePeople":"","updateTime":null,"delFlag":"0"}]
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

    public static class DataBean implements Serializable {
        /**
         * goodsTypeId : 1120879959783661570
         * goodsTypeName : 电脑主机
         * createPeople :
         * createTime : null
         * updatePeople :
         * updateTime : null
         * delFlag : 0
         */

        private String goodsTypeId;
        private String goodsTypeName;
        private String createPeople;
        private Object createTime;
        private String updatePeople;
        private Object updateTime;
        private String delFlag;

        public String getGoodsTypeId() {
            return goodsTypeId;
        }

        public void setGoodsTypeId(String goodsTypeId) {
            this.goodsTypeId = goodsTypeId;
        }

        public String getGoodsTypeName() {
            return goodsTypeName;
        }

        public void setGoodsTypeName(String goodsTypeName) {
            this.goodsTypeName = goodsTypeName;
        }

        public String getCreatePeople() {
            return createPeople;
        }

        public void setCreatePeople(String createPeople) {
            this.createPeople = createPeople;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public String getUpdatePeople() {
            return updatePeople;
        }

        public void setUpdatePeople(String updatePeople) {
            this.updatePeople = updatePeople;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
