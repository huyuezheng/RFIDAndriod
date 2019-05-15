package com.zhl.scanlable.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
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
import com.zhl.scanlable.bean.IPIDBean;
import com.zhl.scanlable.bean.Order;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;
import com.zhl.scanlable.wight.loadlayout.State;

import java.util.ArrayList;
import java.util.Collections;
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

public class FoundActivity extends AppBaseActivity {
    @BindView(R.id.tv_found_ifid)
    TextView tvFoundIfid;
    @BindView(R.id.tv_found_bt)
    TextView tvFoundBt;

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

                    onViewClicked();
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
    private Timer timer;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_found);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UHfData.lsTagList.clear();
        UHfData.dtIndexMap.clear();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        registerReceiver(keyReceiver, filter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        /*   for (UHfData.InventoryTagMap tagMap : data) {
               LogUtil.e(tagMap.strEPC + "//" + tagMap.strRSSI + "//" + tagMap.nReadCount);
           }*/
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (isCanceled)
                    return;
                switch (msg.what) {
                    case MSG_UPDATE_LISTVIEW:
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
        Util.initSoundPool(this);
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

    boolean Scanflag = false;

    @OnClick(R.id.tv_found_bt)
    public void onViewClicked() {
        try {
            if (timer == null) {

                UHfData.lsTagList.clear();
                UHfData.dtIndexMap.clear();
                mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
                mHandler.sendEmptyMessage(MSG_UPDATE_LISTVIEW);
                isCanceled = false;
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (Scanflag)
                            return;
                        Scanflag = true;
                        UHfData.Inventory_6c(0, 0);
                        mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
                        mHandler.sendEmptyMessage(MSG_UPDATE_LISTVIEW);
                        Scanflag = false;
                    }
                }, 0, 5);
                tvFoundBt.setText("查询中...");
                showLoadingDialog("");
            }
        } catch (Exception e) {
            LogUtil.e(e.toString());
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                isCanceled = true;
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    tvFoundBt.setText("查询");
                    List<IPIDBean> ipidBeans = new ArrayList<>();
                    for (UHfData.InventoryTagMap tagMap : UHfData.lsTagList) {
                        LogUtil.e(tagMap.strEPC + "//" + tagMap.strRSSI + "//" + tagMap.nReadCount);
                        IPIDBean ipidBean = new IPIDBean(tagMap.strEPC, tagMap.nReadCount);
                        ipidBeans.add(ipidBean);
                    }
                    Collections.sort(ipidBeans, new Order());
                    if (ipidBeans != null && ipidBeans.size() > 0) {
                        tvFoundIfid.setText(ipidBeans.get(0).getIpid());
                        requestJH(ipidBeans.get(0).getIpid());
                    }
                    UHfData.lsTagList.clear();
                    UHfData.dtIndexMap.clear();
                }
            }
        }, 2500);//3秒后执行Runnable中的run方法
    }

    private void requestJH(String ipid) {
        OkgoBaseModel bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
//        map.put("signalSource", ipid);
        map.put("signalSources", ipid);
//        map.put("assetsTypeNo", "0101");
        bean1OkgoBaseModel.getRequestNet(this, Constant.check_assets_management_new, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                AssetsmanagementBean bean = gson.fromJson(s, AssetsmanagementBean.class);
                if (bean.getCode()==0 && bean.getData() != null && bean.getData().size() > 0) {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra("details", bean.getData().get(0));
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
