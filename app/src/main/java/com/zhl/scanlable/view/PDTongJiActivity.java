package com.zhl.scanlable.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhl.scanlable.R;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.CangKBean;
import com.zhl.scanlable.bean.CheckBean;
import com.zhl.scanlable.bean.TypeBean;
import com.zhl.scanlable.utils.PreferenceUtil;
import com.zhl.scanlable.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PDTongJiActivity extends AppBaseActivity {

    @BindView(R.id.tv_pdqy)
    TextView tvPdqy;
    @BindView(R.id.tv_pdzclx)
    TextView tvPdzclx;
    @BindView(R.id.tv_pdsl)
    TextView tvPdsl;
    @BindView(R.id.tv_sjsl)
    TextView tvSjsl;
    @BindView(R.id.et_pdr)
    EditText etPdr;
    @BindView(R.id.et_bz)
    EditText etBz;
    Gson gson = new Gson();
    private String num;
    private String allnum;
    private TypeBean.DataBean type;
    private CangKBean.DataBean cang;
    private String info;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pd_tongji);
    }

    @Override
    protected void initView() {
        num = getIntent().getStringExtra("num");
        info = getIntent().getStringExtra("info");
        allnum = getIntent().getStringExtra("allnum");
        type = (TypeBean.DataBean) getIntent().getSerializableExtra("type");
        cang = (CangKBean.DataBean) getIntent().getSerializableExtra("cang");
        tvSjsl.setText(allnum);
        tvPdqy.setText(cang.getDepotName());
        tvPdzclx.setText(type.getGoodsTypeName());
        tvPdsl.setText(num);

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

    @OnClick(R.id.tv_tijiao)
    public void onViewClicked() {
        if (etBz.getText().equals("")) {
            ToastUtil.show("请填写备注！");
            return;
        }
        if (etPdr.getText().equals("")) {
            ToastUtil.show("请填写盘点人姓名！");
            return;
        }
        requestNet(etPdr.getText().toString(), etBz.getText().toString());
    }

    private void requestNet(String pdr, String bz) {
        showLoadingDialog("");
        Map<String, String> map = new HashMap<>();
        map.put("checkDepotId", cang.getDepotId());
        map.put("checkGoodsId", type.getGoodsTypeId());
        map.put("checkNumber", num);
        map.put("reallyNumber", allnum);
        map.put("remark", bz);
        map.put("operator", pdr);
        map.put("checkType", info);
        String params = gson.toJson(map);
        String baseUrl = "http://" + PreferenceUtil.getString(getContext(), "IP", "") + ":" + PreferenceUtil.getString(getContext(), "DK", "") + "/";
        String URL = baseUrl + Constant.commitCheckInfo;
        OkGo.<String>post(URL)
                .tag(this)
                .upJson(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CheckBean checkBean = gson.fromJson(response.body(), CheckBean.class);
                        if (checkBean.getCode() == 0) {
                            if (checkBean.isData()) {
                                ToastUtil.show("盘点成功！");
                                finish();
                            } else {
                                ToastUtil.show("盘点失败！");
                            }
                        } else {
                            ToastUtil.show("盘点失败！");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
