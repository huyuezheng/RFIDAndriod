package com.zhl.scanlable.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zhl.scanlable.R;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;
import com.zhl.scanlable.view.adapter.GoodsListAdapter;
import com.zhl.scanlable.view.adapter.GoodsListAdapter2;
import com.zhl.scanlable.wight.loadlayout.OnLoadListener;
import com.zhl.scanlable.wight.loadlayout.OnNoDataListener;
import com.zhl.scanlable.wight.loadlayout.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class GoodsListActivity extends AppBaseActivity {

    @BindView(R.id.lv_goods_list)
    ListView lvGoodsList;
    private ArrayList<String> list = new ArrayList<>();
    List<AssetsmanagementBean.DataBean> dataBeans = new ArrayList<>();
    private GoodsListAdapter2 goodsListAdapter;
    private AssetsmanagementBean bean;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_goods_list);
    }

    @Override
    protected void initView() {
        goodsListAdapter = new GoodsListAdapter2(this, dataBeans);
        lvGoodsList.setAdapter(goodsListAdapter);
        lvGoodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("details", dataBeans.get(i));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void obtainData() {
        showLoadingDialog("");
        String signalSource = "";
        list = getIntent().getStringArrayListExtra("list");
        if (list.size() == 1) {
            signalSource = list.get(0);
        } else {
            for (int i = 0; i < list.size(); i++) {
                AssetsmanagementBean.DataBean dataBean = new AssetsmanagementBean.DataBean();
                dataBean.setGoodsRfid(list.get(i));
                dataBeans.add(dataBean);
                if (i == 0) {
                    signalSource = list.get(0);
                } else {
                    signalSource = signalSource + "," + list.get(i);
                }
            }
        }

        OkgoBaseModel bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("signalSources",  signalSource);
//        map.put("signalSource", "01");
//        map.put("assetsTypeNo", "0101");
        LogUtil.e(signalSource);
        bean1OkgoBaseModel.getRequestNet(this, Constant.check_assets_management_new, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                try {
                    bean = gson.fromJson(s, AssetsmanagementBean.class);
                } catch (Exception e) {
                    LogUtil.e("我是异常!");
                }

                if (bean != null && bean.getCode()==0 && bean.getData() != null && bean.getData().size() > 0) {

                    changeUI(bean);
                } else {
                    goodsListAdapter.notifyDataSetChanged();
                   /* //设置页面为“无数据”状态
                    getLoadLayout().setLayoutState(State.NO_DATA);
                    if (bean != null) {
                        ToastUtil.show(bean.getMsg());

                    }*/
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

    private void changeUI(AssetsmanagementBean bean) {
        dataBeans.clear();
        dataBeans.addAll(bean.getData());
        //设置页面为“成功”状态，显示正文布局
        getLoadLayout().setLayoutState(State.SUCCESS);
        List<String> strings = new ArrayList<>();
        for (AssetsmanagementBean.DataBean dataBean : bean.getData()) {
            strings.add(dataBean.getGoodsRfid());
        }
        List<String> exists = new ArrayList<String>(list);
        exists.removeAll(strings);
        for (String s : exists) {
            AssetsmanagementBean.DataBean dataBean = new AssetsmanagementBean.DataBean();
            dataBean.setGoodsRfid(s);
            dataBeans.add(dataBean);
        }
        goodsListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvent() {
        getLoadLayout().setOnNoDataListener(new OnNoDataListener() {
            @Override
            public void onGoTo() {
                getLoadLayout().setLayoutState(State.LOADING);
            }
        });
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                obtainData();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
