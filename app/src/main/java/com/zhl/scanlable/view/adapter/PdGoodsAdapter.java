package com.zhl.scanlable.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.bean.ArticleBean;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.view.DetailsActivity;

import java.util.List;

public class PdGoodsAdapter extends RecyclerView.Adapter<PdGoodsAdapter.MyViewHolder> {
    private List<ArticleBean.DataBean> mList;
    private Context mContext;

    public PdGoodsAdapter(List<ArticleBean.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pd, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder iv, final int position) {
        iv.tvNum.setText(String.valueOf(position + 1));
        iv.tv_qy.setText(mList.get(position).getArea());
        iv.tv_zcmc.setText(mList.get(position).getArticlename());
        iv.tv_zcgg.setText(mList.get(position).getArticlespec());
        iv.tv_pch.setText(mList.get(position).getSerial());
        iv.tv_zcfz.setText(mList.get(position).getCreateby());
        iv.tv_zcsl.setText(mList.get(position).getRealnum() + "");
        iv.tv_zctm.setText(mList.get(position).getBarcode());
        iv.tv_dw.setText(mList.get(position).getUnit());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public String getCount() {
        return mList.size() + "";
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_qy;
        TextView tv_zcmc;
        TextView tvNum;
        TextView tv_zcgg;
        TextView tv_pch;
        TextView tv_zcfz;
        TextView tv_zcsl;
        TextView tv_zctm;
        TextView tv_dw;


        public MyViewHolder(View view) {
            super(view);
            tvNum = (TextView) view.findViewById(R.id.tv_num);
            tv_qy = (TextView) view.findViewById(R.id.tv_qy);
            tv_zcmc = (TextView) view.findViewById(R.id.tv_zcmc);
            tv_zcgg = (TextView) view.findViewById(R.id.tv_zcgg);
            tv_pch = (TextView) view.findViewById(R.id.tv_pch);
            tv_zcfz = (TextView) view.findViewById(R.id.tv_zcfz);
            tv_zcsl = (TextView) view.findViewById(R.id.tv_zcsl);
            tv_zctm = (TextView) view.findViewById(R.id.tv_zctm);
            tv_dw = (TextView) view.findViewById(R.id.tv_dw);
        }
    }
}
