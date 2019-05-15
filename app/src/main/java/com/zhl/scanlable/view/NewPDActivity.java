package com.zhl.scanlable.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.uhf.scanlable.UHfData;
import com.zhl.scanlable.R;
import com.zhl.scanlable.Util;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.bean.AllKCBean;
import com.zhl.scanlable.bean.AssetsmanagementBean;
import com.zhl.scanlable.bean.CangKBean;
import com.zhl.scanlable.bean.TypeBean;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;
import com.zhl.scanlable.view.adapter.CoodsAdapter;
import com.zhl.scanlable.view.adapter.CustomGridLayoutManager;
import com.zhl.scanlable.wight.loadlayout.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dq on 2018/8/20 0020.
 */

public class NewPDActivity extends AppBaseActivity {

    @BindView(R.id.lv_pandian_list)
    RecyclerView listView;
    @BindView(R.id.tv_pandian_bt)
    TextView tvPandianBt;
    @BindView(R.id.tv_znum)
    TextView txNum;
    @BindView(R.id.tv_pandian_cx)
    TextView tvPandianCx;
    @BindView(R.id.tv_zclx)
    TextView tvZclx;
    @BindView(R.id.tv_ck)
    TextView tvCk;
    @BindView(R.id.tv_kczs)
    TextView tvKczs;
    @BindView(R.id.tv_pandian_pd)
    TextView tvPandianPd;
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
    List<TypeBean.DataBean> typeBean = new ArrayList<>();
    TypeBean.DataBean typeData;
    List<CangKBean.DataBean> cangkBean = new ArrayList<>();
    CangKBean.DataBean cangKData;
    private Dialog typeDialog;
    private Dialog cangKDialog;
    private BaseQuickAdapter typeAdapter;
    private BaseQuickAdapter cangKAdapter;
    private String goodsTypeId = "";
    private String depotId = "";

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_new_pd);
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
        newTypeDialog();
        newCangKDialog();
    }

    public void newTypeDialog() {
        typeDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        //初始化控件
        RecyclerView recyclerView = inflate.findViewById(R.id.rv_list);
        typeAdapter = new BaseQuickAdapter<TypeBean.DataBean, BaseViewHolder>(R.layout.item_type, typeBean) {

            @Override
            protected void convert(BaseViewHolder helper, TypeBean.DataBean item) {
                helper.setText(R.id.tv_type, item.getGoodsTypeName());
            }
        };
//        typeAdapter = new TypeAdapter(typeBean, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvZclx.setText(typeBean.get(position).getGoodsTypeName());
                typeData = typeBean.get(position);
                goodsTypeId = typeData.getGoodsTypeId();
                typeDialog.dismiss();
            }
        });
        //将布局设置给Dialog
        typeDialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = typeDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
    }

    public void newCangKDialog() {
        cangKDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        //初始化控件
        RecyclerView recyclerView = inflate.findViewById(R.id.rv_list);
        cangKAdapter = new BaseQuickAdapter<CangKBean.DataBean, BaseViewHolder>(R.layout.item_type, cangkBean) {

            @Override
            protected void convert(BaseViewHolder helper, CangKBean.DataBean item) {
                helper.setText(R.id.tv_type, item.getDepotName());
            }
        };
        cangKAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvCk.setText(cangkBean.get(position).getDepotName());
                cangKData = cangkBean.get(position);
                depotId = cangKData.getDepotId();
                cangKDialog.dismiss();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cangKAdapter);
        //将布局设置给Dialog
        cangKDialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = cangKDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
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
                            mapData.addAll(UHfData.lsTagList);
                            for (UHfData.InventoryTagMap tagMap : mapData) {
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
        OkgoBaseModel.getInstance().getRequestNetGET(this, Constant.all, null, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                Gson gson = new Gson();
                CangKBean cangKBean = gson.fromJson(s, CangKBean.class);
                if (cangKBean.getCode() == 0 && cangKBean.getData() != null && cangKBean.getData().size() > 0) {
                    cangkBean.addAll(cangKBean.getData());
                    cangKAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void callFailed() {

            }

            @Override
            public void onFinish() {

            }
        });
        OkgoBaseModel.getInstance().getRequestNetGET(this, Constant.allGoodsType, null, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                Gson gson = new Gson();
                TypeBean bean = gson.fromJson(s, TypeBean.class);
                if (bean.getCode() == 0 && bean.getData() != null && bean.getData().size() > 0) {
                    typeBean.addAll(bean.getData());
                    typeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void callFailed() {

            }

            @Override
            public void onFinish() {

            }
        });
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
              /*  AssetsmanagementBean.DataBean dataBean = new AssetsmanagementBean.DataBean();
                dataBean.setGoodsRfid(list.get(i));
                dataBeans.add(dataBean);*/
                if (i == 0) {
                    signalSource = list.get(0);
                } else {
                    signalSource = signalSource + "," + list.get(i);
                }
            }
        }

        bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("goodsRfid", signalSource);
        map.put("goodsTypeId", goodsTypeId);
        map.put("depotId", depotId);
        LogUtil.e(signalSource);
        bean1OkgoBaseModel.getRequestNetGET(this, Constant.getGoodsByRfid, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                try {
                    bean = gson.fromJson(s, AssetsmanagementBean.class);
                } catch (Exception e) {
                    LogUtil.e("我是异常!");
                }

                if (bean != null && bean.getCode() == 0 && bean.getData() != null && bean.getData().size() > 0) {
                    changeUI(bean);
                }
                listHandler.sendEmptyMessage(1);
                txNum.setText(String.valueOf(goodsListAdapter.getCount()));
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
     */
    private void changeUI(AssetsmanagementBean bean) {
        dataBeans.clear();
        dataBeans.addAll(bean.getData());
        //设置页面为“成功”状态，显示正文布局
        getLoadLayout().setLayoutState(State.SUCCESS);
       /* List<String> strings = new ArrayList<>();
        for (AssetsmanagementBean.DataBean dataBean : bean.getData()) {
            strings.add(dataBean.getGoodsRfid());
        }
        List<String> exists = new ArrayList<String>(list);
//        exists.removeAll(strings);
        for (String s : exists) {
            AssetsmanagementBean.DataBean dataBean = new AssetsmanagementBean.DataBean();
            dataBean.setGoodsRfid(s);
            dataBeans.add(dataBean);
        }*/
    }


    @OnClick({R.id.tv_pandian_bt, R.id.tv_pandian_pd, R.id.tv_pandian_cx, R.id.tv_zclx, R.id.tv_ck})
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
                if (typeData == null) {
                    ToastUtil.show("请选择查询资产类型！");
                    return;
                }
                if (cangKData == null) {
                    ToastUtil.show("请选择查询仓库！");
                    return;
                }
                if (tvKczs.getText().equals("0")) {
                    ToastUtil.show("库存为0，请先查询库存！");
                    return;
                }
                if (bean != null && bean.getData() != null && bean.getData().size() != 0) {
                    intent = new Intent(getContext(), PDTongJiActivity.class);
                    intent.putExtra("num", bean.getData().size() + "");
                    intent.putExtra("allnum", tvKczs.getText());
                    intent.putExtra("type", typeData);
                    intent.putExtra("cang", cangKData);
                    intent.putExtra("info", "1");
                    startActivity(intent);

                } else {
                    ToastUtil.show("请先查询！");
                }

                break;
            case R.id.tv_pandian_cx:
                intent = new Intent(getContext(), PDTongJiActivity.class);
                break;
            case R.id.tv_ck:
                cangKDialog.show();
                break;
            case R.id.tv_zclx:
                typeDialog.show();
                break;
        }
    }

    @OnClick(R.id.tv_pandian_cxkc)
    public void onViewClicked() {
        if (typeData == null) {
            ToastUtil.show("请选择查询资产类型！");
            return;
        }
        if (cangKData == null) {
            ToastUtil.show("请选择查询仓库！");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("goodsTypeId", typeData.getGoodsTypeId());
        map.put("depotId", cangKData.getDepotId());
        OkgoBaseModel.getInstance().getRequestNetGET(this, Constant.getGoodsCount, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                Gson gson = new Gson();
                AllKCBean allKCBean = gson.fromJson(s, AllKCBean.class);
                if (allKCBean.getCode() == 0) {
                    if (allKCBean.getData() == 0) {
                        ToastUtil.show("没有查询到库存");
                    } else {
                        tvKczs.setText(allKCBean.getData() + "");
                    }
                } else {
                    ToastUtil.show(allKCBean.getMsg());
                }

            }

            @Override
            public void callFailed() {
                ToastUtil.show("查询失败，请检查网络！");
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 盘点
     *
     * @param strings
     */
   /* private void panDian(List<AssetsmanagementBean.DataBean> strings) {
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

