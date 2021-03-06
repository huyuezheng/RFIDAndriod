package com.zhl.scanlable.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.bean.CangKBean;
import com.zhl.scanlable.bean.TypeBean;

import java.util.List;

public class CangKAdapter extends RecyclerView.Adapter<CangKAdapter.MyViewHolder> {
    private List<CangKBean.DataBean> mList;
    private Context mContext;

    public CangKAdapter(List<CangKBean.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder iv, final int position) {
        iv.tvType.setText(mList.get(position).getDepotName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public String getCount() {
        return mList.size() + "";
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvType;

        public MyViewHolder(View view) {
            super(view);
            tvType = (TextView) view.findViewById(R.id.tv_type);
        }
    }
}
