package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

public class CangKBean implements Serializable {


    /**
     * msg : success
     * code : 0
     * data : [{"depotId":"1114415808890740737","depotName":"石燕湖仓库","depotManagerId":"石燕湖管理员","depotAddress":"石燕湖景区","depotPhone":"010-12345678","isActive":0,"createTime":1554532408000,"createPeople":null,"updateTime":1554532462000,"updatePeople":null,"delFlag":"0"},{"depotId":"1114416764575490049","depotName":"石牛寨仓库","depotManagerId":"石牛寨管理员","depotAddress":"石牛寨景区","depotPhone":"111111","isActive":0,"createTime":1554532636000,"createPeople":null,"updateTime":1554532645000,"updatePeople":null,"delFlag":"0"},{"depotId":"1114449880719515650","depotName":"电商科技公司","depotManagerId":"12","depotAddress":"长沙市天心区星城荣域","depotPhone":"123321","isActive":0,"createTime":1554540531000,"createPeople":null,"updateTime":null,"updatePeople":null,"delFlag":"0"}]
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
         * depotId : 1114415808890740737
         * depotName : 石燕湖仓库
         * depotManagerId : 石燕湖管理员
         * depotAddress : 石燕湖景区
         * depotPhone : 010-12345678
         * isActive : 0
         * createTime : 1554532408000
         * createPeople : null
         * updateTime : 1554532462000
         * updatePeople : null
         * delFlag : 0
         */

        private String depotId;
        private String depotName;
        private String depotManagerId;
        private String depotAddress;
        private String depotPhone;
        private int isActive;
        private long createTime;
        private Object createPeople;
        private long updateTime;
        private Object updatePeople;
        private String delFlag;

        public String getDepotId() {
            return depotId;
        }

        public void setDepotId(String depotId) {
            this.depotId = depotId;
        }

        public String getDepotName() {
            return depotName;
        }

        public void setDepotName(String depotName) {
            this.depotName = depotName;
        }

        public String getDepotManagerId() {
            return depotManagerId;
        }

        public void setDepotManagerId(String depotManagerId) {
            this.depotManagerId = depotManagerId;
        }

        public String getDepotAddress() {
            return depotAddress;
        }

        public void setDepotAddress(String depotAddress) {
            this.depotAddress = depotAddress;
        }

        public String getDepotPhone() {
            return depotPhone;
        }

        public void setDepotPhone(String depotPhone) {
            this.depotPhone = depotPhone;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getCreatePeople() {
            return createPeople;
        }

        public void setCreatePeople(Object createPeople) {
            this.createPeople = createPeople;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdatePeople() {
            return updatePeople;
        }

        public void setUpdatePeople(Object updatePeople) {
            this.updatePeople = updatePeople;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
