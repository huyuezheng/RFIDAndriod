package com.zhl.scanlable.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uhf.scanlable.UHfData;
import com.zhl.scanlable.R;
import com.zhl.scanlable.Util;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.bean.PanDianBean;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;
import com.zhl.scanlable.view.adapter.CoodsAdapter;
import com.zhl.scanlable.view.adapter.CustomGridLayoutManager;
import com.zhl.scanlable.view.adapter.GoodsListAdapter2;
import com.zhl.scanlable.wight.loadlayout.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class PanDianActivity extends AppBaseActivity {

    @BindView(R.id.lv_pandian_list)
    RecyclerView listView;
    @BindView(R.id.tv_pandian_bt)
    TextView tvPandianBt;
    @BindView(R.id.tv_znum)
    TextView txNum;
    @BindView(R.id.tv_pandian_cx)
    TextView tvPandianCx;
    private long startTime = 0;
    private boolean keyUpFalg = true;
    private static final int MSG_UPDATE_LISTVIEW = 0;
    private boolean isCanceled = true;


    private BroadcastReceiver keyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int keyCode = intent.getIntExtra("keyCode", 0);
            // H941
            if (keyCode == 0) {
                keyCode = intent.getIntExtra("keycode", 0);
            }
            boolean keyDown = intent.getBooleanExtra("keydown", false);
            if (keyUpFalg && keyDown && System.currentTimeMillis() - startTime > 500) {
                keyUpFalg = false;
                startTime = System.currentTimeMillis();
                if (keyCode == KeyEvent.KEYCODE_F3) {
                    onViewClicked(tvPandianBt);
                }
                return;
            } else if (keyDown) {
                startTime = System.currentTimeMillis();
            } else {
                keyUpFalg = true;
            }

        }
    };
    private Handler mHandler;
    //    private MyListAdapter myAdapter;
    private Timer timer;
    private boolean scanflag = false;
    private Intent intent;
    private AssetsmanagementBean bean;
    private boolean isEnd = true;
    private OkgoBaseModel bean1OkgoBaseModel;
    private Gson gson;
    private Handler listHandler;
    private CoodsAdapter goodsListAdapter;
    List<AssetsmanagementBean.DataBean> dataBeans = new ArrayList<>();
    private CustomGridLayoutManager layoutManager;
    //    private GoodsListAdapter2 goodsListAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pandian);
    }


    @Override
    protected void initView() {
        UHfData.lsTagList.clear();
        UHfData.dtIndexMap.clear();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        registerReceiver(keyReceiver, filter);
        goodsListAdapter = new CoodsAdapter(dataBeans, this);
        layoutManager = new CustomGridLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(goodsListAdapter);
        layoutManager.setScrollEnabled(false);

      /*  goodsListAdapter = new GoodsListAdapter2(this, dataBeans);
        listView.setAdapter(goodsListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("details", dataBeans.get(i));
                startActivity(intent);
            }
        });*/
    }

    ArrayList<String> strings = new ArrayList<>();
    ArrayList<UHfData.InventoryTagMap> mapData = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    @Override
    protected void obtainData() {
        try {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (isCanceled)
                        return;
                    switch (msg.what) {
                        case MSG_UPDATE_LISTVIEW:
                            mapData.clear();
                            mapData.addAll( UHfData.lsTagList);
                            for (UHfData.InventoryTagMap tagMap :mapData) {
                                LogUtil.e(tagMap.strEPC + "//" + tagMap.strRSSI + "//" + tagMap.nReadCount);
                                if (!strings.contains(tagMap.strEPC)) {
                                    strings.add(tagMap.strEPC);
                                }
                            }
                            if (strings.size() != 0 && isEnd) {

                                requestNet(strings);
                            }
//                            myAdapter.notifyDataSetChanged();
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!Util.soundfinished)
                                        Util.play(1, 0);
                                }
                            }).start();
                            break;
                        default:
                            break;
                    }
                    super.handleMessage(msg);
                }

            };
        } catch (Exception e) {
        }
        Util.initSoundPool(this);
        listHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                goodsListAdapter.setData(dataBeans);
//                listView.scrollToPosition(dataBeans.size() - 1);
//                goodsListAdapter.notifyDataSetChanged();
                super.handleMessage(msg);
            }
        };

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelScan();
        unregisterReceiver(keyReceiver);
    }

    private void cancelScan() {
        isCanceled = true;
        mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
        if (timer != null) {
            timer.cancel();
            timer = null;
            UHfData.lsTagList.clear();
            UHfData.dtIndexMap.clear();
        }
    }

    /**
     * 查询
     *
     * @param list
     */
    public void requestNet(final ArrayList<String> list) {
        isEnd = false;
        String signalSource = "";
        dataBeans.clear();
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

        bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("signalSources", signalSource);
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
                    changeUI(bean, list);
                }
                listHandler.sendEmptyMessage(1);
                txNum.setText(String.valueOf("总数: " + goodsListAdapter.getCount()));
            }

            @Override
            public void callFailed() {

                ToastUtil.show("网络异常,请检查网络!");
            }

            @Override
            public void onFinish() {
                isEnd = true;
                dismissLoadingDialog();
            }
        });
    }


    /**
     * 刷新页面
     *
     * @param bean
     * @param list
     */
    private void changeUI(AssetsmanagementBean bean, ArrayList<String> list) {
        dataBeans.clear();
        dataBeans.addAll(bean.getData());
        //设置页面为“成功”状态，显示正文布局
        getLoadLayout().setLayoutState(State.SUCCESS);
        List<String> strings = new ArrayList<>();
        for (AssetsmanagementBean.DataBean dataBean : bean.getData()) {
            strings.add(dataBean.getGoodsRfid());
        }
        List<String> exists = new ArrayList<String>(list);
//        exists.removeAll(strings);
        for (String s : exists) {
            AssetsmanagementBean.DataBean dataBean = new AssetsmanagementBean.DataBean();
            dataBean.setGoodsRfid(s);
            dataBeans.add(dataBean);
        }
    }


    @OnClick({R.id.tv_pandian_bt, R.id.tv_pandian_pd, R.id.tv_pandian_cx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pandian_bt:
                try {
                    if (timer == null) {
                        if (goodsListAdapter != null) {
                            txNum.setText("0");
                            UHfData.lsTagList.clear();
                            UHfData.dtIndexMap.clear();
                            listHandler.sendEmptyMessage(1);
                            mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
                            mHandler.sendEmptyMessage(MSG_UPDATE_LISTVIEW);
                        }
                        layoutManager.setScrollEnabled(false);
                        isCanceled = false;
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (scanflag)
                                    return;
                                scanflag = true;
                                UHfData.Inventory_6c(0, 0);
//						UHfData.Inventory_6c_Mask((byte)0, 16, 0, UHfGetData.hexStringToBytes("E200"));
                                mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
                                mHandler.sendEmptyMessage(MSG_UPDATE_LISTVIEW);
                                scanflag = false;
                            }
                        }, 0, 100);
                        tvPandianBt.setText("停止扫描");
                    } else {
                        isCanceled = true;
                        layoutManager.setScrollEnabled(true);
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                            tvPandianBt.setText("开始扫描");
                        }
                    }
                } catch (Exception e) {
                }
                break;
            case R.id.tv_pandian_pd:
                if (bean != null && bean.getData() != null && bean.getData().size() != 0) {
//                    panDian(bean.getData());
                }

                break;
            case R.id.tv_pandian_cx:
                break;
        }
    }

   /* *//**
     * 盘点
     *
     * @param strings
     *//*
    private void panDian(List<AssetsmanagementBean.DataBean> strings) {
        showLoadingDialog("");
        List<String> list = new ArrayList<>();
        List<PanDianBean> panDianBeans = new ArrayList<>();
        for (AssetsmanagementBean.DataBean dataBean : strings) {
            list.add(dataBean.getBarcode());
        }
        Set<String> uniqueSet = new HashSet(list);
        for (String temp : uniqueSet) {
            panDianBeans.add(new PanDianBean(temp, Collections.frequency(list, temp)));
        }
        gson = new Gson();
        String params = gson.toJson(panDianBeans);
        Log.e("ddd", panDianBeans.toString());
        Map<String, String> map = new HashMap<>();
        map.put("stockCheckEntity", params);
        bean1OkgoBaseModel.getRequestNet(this, Constant.add_stock_check_entity, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                AssetsmanagementBean bean = gson.fromJson(s, AssetsmanagementBean.class);
                if (bean.getCode().equals("00")) {
                    ToastUtil.show("盘点成功!");
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
                isEnd = true;
                dismissLoadingDialog();
            }
        });
    }*/
}

