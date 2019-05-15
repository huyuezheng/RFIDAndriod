package com.zhl.scanlable.bean;

import java.io.Serializable;
import java.util.List;

public class ArticleBean implements Serializable {

    /**
     * msg : 成功
     * code : 00
     * data : [{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-6600CPU、8G内存、120G固态+500G硬盘、飞利浦24寸LED","unit":"台","createby":"徐前","serial":"20181229102810","barcode":"SN-E-0099"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-4460CPU、4G内存、120G固态+500G硬盘、三星19寸LED","unit":"台","createby":"唐圣建","serial":"20181229102810","barcode":"SN-E-0148"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-6600CPU、8G内存、120G固态+500G硬盘、三星24寸LED","unit":"台","createby":"郑婕妤","serial":"20181229102810","barcode":"SN-E-0100"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-4460CPU、4G内存、120G固态+500G硬盘、三星19寸LED","unit":"台","createby":"黄关关","serial":"20181229102810","barcode":"SN-E-0141"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"组装电脑AOC21.5寸","unit":"台","createby":"肖业","serial":"20181229102810","barcode":"SN-E-0347"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-7500cpu、8G内存、120G固态+1T机械硬盘、21.5英寸AOC显示器","unit":"台","createby":"龚胜男","serial":"20181229102810","barcode":"SN-E-0156"},{"area":"电商科技公司","articlename":"43 触控查询机（竖）","realnum":1,"articlespec":"","unit":"台","createby":"刘泓强","serial":"20181229102810","barcode":"SN-E-0244"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-6600CPU、8G内存、120G固态+500G硬盘、三星24寸LED","unit":"台","createby":"王追","serial":"20181229102810","barcode":"SN-E-0098"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-6600CPU、8G内存、120G固态+500G硬盘、AOC21.5寸LED","unit":"台","createby":"王佳林","serial":"20181229102810","barcode":"SN-E-0134"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-7500U、8G内存、120G固态+500G机械硬盘 19寸Philips 显示器","unit":"台","createby":"杨丽","serial":"20181229102810","barcode":"SN-E-0348"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-7500 CPU ,8G内存,120固态 +1T机械硬盘 21.5英寸AOC 显示器","unit":"台","createby":"邓志","serial":"20181229102810","barcode":"SN-E-0342"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"I7-6700CPU ,8G内存,120固态 +1T机械硬盘 19寸Philips 显示器","unit":"台","createby":"贺光明","serial":"20181229102810","barcode":"SN-E-0155"},{"area":"研发部-智慧景区事业部","articlename":"台式电脑","realnum":1,"articlespec":"i5-7500cpu、金士顿8G内存、铭瑄 120GB A6固态硬盘+1T机械硬盘、冠捷 AOC2280（21.7英寸）显示器","unit":"台","createby":"武欢","serial":"20181229102810","barcode":"SN-E-0341"}]
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

    public static class DataBean implements Serializable{
        /**
         * area : 研发部-智慧景区事业部
         * articlename : 台式电脑
         * realnum : 1
         * articlespec : i5-6600CPU、8G内存、120G固态+500G硬盘、飞利浦24寸LED
         * unit : 台
         * createby : 徐前
         * serial : 20181229102810
         * barcode : SN-E-0099
         */

        private String area;
        private String articlename;
        private int realnum;
        private String articlespec;
        private String unit;
        private String createby;
        private String serial;
        private String barcode;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArticlename() {
            return articlename;
        }

        public void setArticlename(String articlename) {
            this.articlename = articlename;
        }

        public int getRealnum() {
            return realnum;
        }

        public void setRealnum(int realnum) {
            this.realnum = realnum;
        }

        public String getArticlespec() {
            return articlespec;
        }

        public void setArticlespec(String articlespec) {
            this.articlespec = articlespec;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCreateby() {
            return createby;
        }

        public void setCreateby(String createby) {
            this.createby = createby;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
    }
}
