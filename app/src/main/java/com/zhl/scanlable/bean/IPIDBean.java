package com.zhl.scanlable.bean;

import android.support.annotation.NonNull;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class IPIDBean {
    private String Ipid;
    private int num;

    public IPIDBean(String ipid, int num) {
        Ipid = ipid;
        this.num = num;
    }

    public String getIpid() {
        return Ipid;
    }

    public void setIpid(String ipid) {
        Ipid = ipid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
