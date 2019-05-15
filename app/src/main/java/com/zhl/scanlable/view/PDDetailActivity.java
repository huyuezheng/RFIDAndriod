package com.zhl.scanlable.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.ArticleBean;
import com.zhl.scanlable.view.adapter.PdGoodsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PDDetailActivity extends AppBaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pd_detail);
    }

    @Override
    protected void initView() {
        ArticleBean articleBean = (ArticleBean) getIntent().getSerializableExtra("data");
        PdGoodsAdapter pdGoodsAdapter = new PdGoodsAdapter(articleBean.getData(), this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(pdGoodsAdapter);
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
