package com.zhl.scanlable.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.utils.PreferenceUtil;
import com.zhl.scanlable.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class SettingActivity extends AppBaseActivity {
    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_dk)
    EditText etDk;
    @BindView(R.id.et_sbh)
    EditText etSbh;
    @BindView(R.id.tv_tm)
    EditText tvTm;
    @BindView(R.id.tv_setting_bt)
    TextView tvSettingBt;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        String ip = PreferenceUtil.getString(this, "IP", "");
        String dk = PreferenceUtil.getString(this, "DK", "");
        etIp.setText(ip);
        etDk.setText(dk);
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

    @OnClick(R.id.tv_setting_bt)
    public void onViewClicked() {
        if (etIp.getText().toString().isEmpty()) {
            ToastUtil.show("ip不能为空!");
            return;
        }
        if (etDk.getText().toString().isEmpty()) {
            ToastUtil.show("端口不能为空!");
            return;
        }
        PreferenceUtil.putString(this, "IP", etIp.getText().toString());
        PreferenceUtil.putString(this, "DK", etDk.getText().toString());
        ToastUtil.show("保存成功!");
        finish();

    }
}
