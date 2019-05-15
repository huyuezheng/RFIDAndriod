package com.zhl.scanlable.bean;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class Order implements Comparator<IPIDBean> {

    public int compare(IPIDBean lhs, IPIDBean rhs) {
        // TODO Auto-generated method stub
        return rhs.getNum() - lhs.getNum();
    }

}
