package com.zhl.scanlable.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllPDActivity extends AppBaseActivity {

    private Intent intent;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_all_pd);
    }

    @Override
    protected void initView() {

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

    @OnClick({R.id.ll_all_cangku, R.id.ll_all_bumen, R.id.ll_all_lsd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_all_cangku:
                intent = new Intent(getContext(),NewPDActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_all_bumen:
                intent = new Intent(getContext(),BuMenPDActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_all_lsd:
                intent = new Intent(getContext(),LSDPDActivity.class);
                startActivity(intent);
                break;
        }
    }
}
