package com.zhl.scanlable.view;

import android.os.Bundle;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class DetailsActivity extends AppBaseActivity {
    @BindView(R.id.tv_tm)
    TextView tvTm;
    @BindView(R.id.tv_mc)
    TextView tvMc;
    @BindView(R.id.tv_gg)
    TextView tvGg;
    @BindView(R.id.tv_qy)
    TextView tvQy;
    private AssetsmanagementBean.DataBean dataBean;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void initView() {
        dataBean = (AssetsmanagementBean.DataBean) getIntent().getSerializableExtra("details");
        if (dataBean == null) {
            ToastUtil.show("数据异常");
            return;
        }
        tvTm.setText(dataBean.getGoodsQrcode());
        tvMc.setText(dataBean.getGoodsName());
//        tvGg.setText(dataBean.getArticlespec());
//        tvQy.setText(dataBean.getArticleTypeName());

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
