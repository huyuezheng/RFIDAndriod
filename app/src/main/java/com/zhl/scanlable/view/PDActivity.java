package com.zhl.scanlable.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zhl.scanlable.R;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.ArticleBean;
import com.zhl.scanlable.bean.QueryAssetsTypeBean;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PDActivity extends AppBaseActivity {
    @BindView(R.id.et_pd_id)
    EditText etPdId;
    private ArticleBean bean;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pd);
    }

    @Override
    protected void initView() {
        OkgoBaseModel.getInstance().getRequestNetGET(this, Constant.all, null, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
               /* Gson gson = new Gson();
                bean = gson.fromJson(s, ArticleBean.class);
                if (bean.getCode().equals("00") && bean.getData() != null && bean.getData().size() > 0) {
                    Intent intent = new Intent(PDActivity.this, PDDetailActivity.class);
                    intent.putExtra("data", bean);
                    startActivity(intent);
                } else {
                    ToastUtil.show(bean.getMsg());

                }*/
            }

            @Override
            public void callFailed() {
                ToastUtil.show("网络异常,请检查网络!");
            }

            @Override
            public void onFinish() {
                dismissLoadingDialog();
            }
        });
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

    @OnClick(R.id.tv_pd_bt)
    public void onViewClicked() {
        showLoadingDialog("");
        String id = etPdId.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        map.put("serial", id);
        OkgoBaseModel.getInstance().getRequestNet(this, Constant.query_check_article, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                bean = gson.fromJson(s, ArticleBean.class);
                if (bean.getCode().equals("00") && bean.getData() != null && bean.getData().size() > 0) {
                    Intent intent = new Intent(PDActivity.this, PDDetailActivity.class);
                    intent.putExtra("data", bean);
                    startActivity(intent);
                } else {
                    ToastUtil.show(bean.getMsg());

                }
            }

            @Override
            public void callFailed() {
                ToastUtil.show("网络异常,请检查网络!");
            }

            @Override
            public void onFinish() {
                dismissLoadingDialog();
            }
        });
    }
}
