package com.zhl.scanlable.bean;

import java.util.List;

/**
 * Created by dq on 2018/8/22 0022.
 */

public class QueryAssetsTypeBean {

    /**
     * msg : 成功
     * code : 00
     * data : [{"assetsTypeNo":"01","assetsTypeName":"智慧景区","fatherNode":""},{"assetsTypeNo":"02","assetsTypeName":"营销部门","fatherNode":""},{"assetsTypeNo":"0101","assetsTypeName":"智慧景区办公室","fatherNode":"01"},{"assetsTypeNo":"0201","assetsTypeName":"营销部门办公室","fatherNode":"02"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * assetsTypeNo : 01
         * assetsTypeName : 智慧景区
         * fatherNode :
         */

        private String assetsTypeNo;
        private String assetsTypeName;
        private String fatherNode;

        public String getAssetsTypeNo() {
            return assetsTypeNo;
        }

        public void setAssetsTypeNo(String assetsTypeNo) {
            this.assetsTypeNo = assetsTypeNo;
        }

        public String getAssetsTypeName() {
            return assetsTypeName;
        }

        public void setAssetsTypeName(String assetsTypeName) {
            this.assetsTypeName = assetsTypeName;
        }

        public String getFatherNode() {
            return fatherNode;
        }

        public void setFatherNode(String fatherNode) {
            this.fatherNode = fatherNode;
        }
    }
}
