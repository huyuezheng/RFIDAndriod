package com.zhl.scanlable.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.view.DetailsActivity;

import java.util.List;

public class CoodsAdapter extends RecyclerView.Adapter<CoodsAdapter.MyViewHolder> {
    private List<AssetsmanagementBean.DataBean> mList;
    private Context mContext;

    public CoodsAdapter(List<AssetsmanagementBean.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list3, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder iv, final int position) {
        iv.tvNum.setText(String.valueOf(position + 1));
        iv.tvIfid.setText(mList.get(position).getGoodsRfid());
        if (mList.get(position).getGoodsName() == null) {
            iv.tvType.setText("未激活");
            iv.tvZcbh.setText("");
        } else {
            iv.tvType.setText(mList.get(position).getGoodsName());
            iv.tvZcbh.setText(mList.get(position).getGoodsQrcode());

        }
        iv.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                Log.e("okgo", position + "");
                intent.putExtra("details", mList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public String getCount() {
        return mList.size() + "";
    }

    public void setData(List<AssetsmanagementBean.DataBean> dataBeans) {
        mList = dataBeans;

        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum;
        TextView tvIfid;
        TextView tvType;
        TextView tvZcbh;

        public MyViewHolder(View view) {
            super(view);
            tvNum = (TextView) view.findViewById(R.id.tv_num);
            tvIfid = (TextView) view.findViewById(R.id.tv_ifid);
            tvType = (TextView) view.findViewById(R.id.tv_type);
            tvZcbh = (TextView) view.findViewById(R.id.tv_zcbh);
        }
    }
}
