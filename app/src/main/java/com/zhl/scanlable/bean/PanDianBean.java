package com.zhl.scanlable.bean;

public class PanDianBean {

    /**
     * barcode : SN-E-0098
     * realnum : 1
     */

    private String barcode;
    private int  realnum;

    public PanDianBean(String barcode, int realnum) {
        this.barcode = barcode;
        this.realnum = realnum;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getRealnum() {
        return realnum;
    }

    public void setRealnum(int realnum) {
        this.realnum = realnum;
    }

    @Override
    public String toString() {
        return "PanDianBean{" +
                "barcode='" + barcode + '\'' +
                ", realnum=" + realnum +
                '}';
    }
}
