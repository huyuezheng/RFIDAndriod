package com.zhl.scanlable.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.bean.AssetsmanagementBean;

import java.util.List;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class GoodsListAdapter2 extends BaseAdapter {
    private Context mContext;
    private List<AssetsmanagementBean.DataBean> mList;
    private LayoutInflater layoutInflater;

    public GoodsListAdapter2(Context context, List<AssetsmanagementBean.DataBean> list) {
        mContext = context;
        mList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewParent) {
        // TODO Auto-generated method stub
        ItemView iv = null;
        if (view == null) {
            iv = new ItemView();
            view = layoutInflater.inflate(R.layout.list3, null);
            iv.tvNum = (TextView) view.findViewById(R.id.tv_num);
            iv.tvIfid = (TextView) view.findViewById(R.id.tv_ifid);
            iv.tvType = (TextView) view.findViewById(R.id.tv_type);
            iv.tvZcbh = (TextView) view.findViewById(R.id.tv_zcbh);
            view.setTag(iv);
        } else {
            iv = (ItemView) view.getTag();
        }
        iv.tvNum.setText(String.valueOf(position + 1));
        iv.tvIfid.setText(mList.get(position).getGoodsRfid());
        if (mList.get(position).getGoodsName()==null){
            iv.tvType.setText("未激活");
            iv.tvZcbh.setText("");
        }else {
            iv.tvType.setText(mList.get(position).getGoodsName());
            iv.tvZcbh.setText(mList.get(position).getGoodsQrcode());

        }
        return view;
    }


    public class ItemView {
        TextView tvNum;
        TextView tvIfid;
        TextView tvType;
        TextView tvZcbh;

    }
}
