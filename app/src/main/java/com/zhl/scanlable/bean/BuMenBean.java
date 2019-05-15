package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

public class BuMenBean implements Serializable {

    /**
     * msg : success
     * code : 0
     * data : [{"departmentId":"1114415808890740737","departmentName":"测试部门","departmentChargeMan":"孟繁光","departmentChargeManPhone":"1886745614","delFlag":0}]
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
         * departmentId : 1114415808890740737
         * departmentName : 测试部门
         * departmentChargeMan : 孟繁光
         * departmentChargeManPhone : 1886745614
         * delFlag : 0
         */

        private String departmentId;
        private String departmentName;
        private String departmentChargeMan;
        private String departmentChargeManPhone;
        private int delFlag;

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getDepartmentChargeMan() {
            return departmentChargeMan;
        }

        public void setDepartmentChargeMan(String departmentChargeMan) {
            this.departmentChargeMan = departmentChargeMan;
        }

        public String getDepartmentChargeManPhone() {
            return departmentChargeManPhone;
        }

        public void setDepartmentChargeManPhone(String departmentChargeManPhone) {
            this.departmentChargeManPhone = departmentChargeManPhone;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
