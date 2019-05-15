package com.zhl.scanlable.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uhf.scanlable.UHfData;
import com.zhl.scanlable.R;
import com.zhl.scanlable.ScanMode;

import java.util.List;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class MyListAdapter extends BaseAdapter {
    private Context mContext;
    private List<UHfData.InventoryTagMap> mList;
    private LayoutInflater layoutInflater;

    public MyListAdapter(Context context, List<UHfData.InventoryTagMap> list) {
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

    public List<UHfData.InventoryTagMap> getData() {
        return mList;
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
            view = layoutInflater.inflate(R.layout.list2, null);
            iv.tvNum = (TextView) view.findViewById(R.id.tv_num);
            iv.tvId = (TextView) view.findViewById(R.id.tv_id);
            view.setTag(iv);
        } else {
            iv = (ItemView) view.getTag();
        }
        String epc = mList.get(position).strEPC;
        Integer findIndex = UHfData.dtIndexMap.get(epc);
        if (findIndex != null) {
            iv.tvNum.setText(String.valueOf(position + 1));
            iv.tvId.setText(epc);

        }

        return view;
    }

    public void setData(List<UHfData.InventoryTagMap> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public class ItemView {
        TextView tvNum;
        TextView tvId;

    }
}
