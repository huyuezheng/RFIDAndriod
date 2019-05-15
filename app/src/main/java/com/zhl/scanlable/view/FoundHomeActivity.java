package com.zhl.scanlable.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dq on 2018/8/24 0024.
 */

public class FoundHomeActivity extends AppBaseActivity {

    private Intent intent;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_found_home);
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

    @OnClick({R.id.ll_home_quyu, R.id.ll_home_dange, R.id.ll_home_duoge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_quyu:
                break;
            case R.id.ll_home_dange:
                intent = new Intent(getContext(), FoundActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_duoge:
                intent = new Intent(getContext(), PanDianActivity.class);
                startActivity(intent);
                break;
        }
    }
}
