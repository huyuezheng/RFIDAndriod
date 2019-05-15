package com.zhl.scanlable.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.zhl.scanlable.bean.JiHuoBean;
import com.zhl.scanlable.bean.Order;
import com.zhl.scanlable.bean.QueryAssetsTypeBean;
import com.zhl.scanlable.utils.ToastUtil;
import com.zhl.scanlable.utils.log.LogUtil;

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
 * Created by dq on 2018/8/16 0016.
 */

public class JihuoActivity extends AppBaseActivity {
    @BindView(R.id.tv_jihuo_ifid)
    TextView tvJihuoIfid;
    @BindView(R.id.spiner)
    Spinner spiner;
    @BindView(R.id.et_jihuo_ywm)
    EditText etJihuoYwm;
    @BindView(R.id.tv_jihuo_bt)
    TextView tvJihuoBt;
    private static List<UHfData.InventoryTagMap> data;
    @BindView(R.id.tv_num)
    TextView tvNum;
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
//                    onClick(scan);
                    if (etJihuoYwm.getText().toString().trim() == null) {
                        ToastUtil.show("请输入一维码!");
                        return;
                    }
                    if (etJihuoYwm.getText().length() < 5) {
                        ToastUtil.show("请输入正确的一维码!");
                        return;
                    }
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
    private Timer timer;
    private Handler mHandler;
    private Intent intent;
    private QueryAssetsTypeBean bean;
    private ArrayAdapter adapter;
    List<String> dataBeans = new ArrayList<>();
    private String id;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_jihuo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UHfData.lsTagList.clear();
        UHfData.dtIndexMap.clear();
//        etJihuoYwm.setText("10100100500006FF");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        registerReceiver(keyReceiver, filter);
        data = new ArrayList<>();
    }

    @Override
    protected void initView() {
        String[] arr = {"营销公司", "智慧景区", "电商云平台", "石燕湖景区", "石牛寨景区", "新媒体事业部"};
        //创建ArrayAdapter对象
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dataBeans);
//        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item);

        spiner.setAdapter(adapter);
        etJihuoYwm.addTextChangedListener(textWatcher);

        spiner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id = bean.getData().get(i).getAssetsTypeNo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }

    private CharSequence temp;    // 监听前的文本
    private int editStart;      // 光标开始位置
    private int editEnd;        // 光标结束位置

    TextWatcher textWatcher = new TextWatcher() {
        // 输入文本之前的状态
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        // 输入文本中的状态
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //temp = s   用于记录当前正在输入文本的个数
            temp = s;

        }

        // 输入文本之后的状态
        @Override
        public void afterTextChanged(Editable s) {
            editStart = etJihuoYwm.getSelectionStart();
            editEnd = etJihuoYwm.getSelectionEnd();
            tvNum.setText(temp.length() + "");  //把输入temp中记录的字符个数显示在TextView上
            /*if (temp.length() > 14) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                etJihuoYwm.setText(s);
                etJihuoYwm.setSelection(tempSelection);
            }*/
        }
    };


    @Override
    protected void obtainData() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (isCanceled)
                    return;
                switch (msg.what) {
                    case MSG_UPDATE_LISTVIEW:
                        data = UHfData.lsTagList;
                     /*   for (UHfData.InventoryTagMap tagMap : data) {
                            LogUtil.e(tagMap.strEPC + "//" + tagMap.strRSSI + "//" + tagMap.nReadCount);
                        }*/
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
//        requestQY();
    }

    private void requestQY() {
        showLoadingDialog("");
        OkgoBaseModel bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();

        bean1OkgoBaseModel.getRequestNet(this, Constant.query_assets_type, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                bean = gson.fromJson(s, QueryAssetsTypeBean.class);
                if (bean.getCode().equals("00") && bean.getData() != null && bean.getData().size() > 0) {
                    for (QueryAssetsTypeBean.DataBean dataBean : bean.getData()) {
                        dataBeans.add(dataBean.getAssetsTypeName());
                    }
                    adapter.notifyDataSetChanged();
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

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelScan();
        unregisterReceiver(keyReceiver);
    }

    private void cancelScan() {
        isCanceled = true;
        mHandler.removeMessages(MSG_UPDATE_LISTVIEW);
        if (timer != null) {
            timer.cancel();
            timer = null;
            tvJihuoBt.setText("激活");
            UHfData.lsTagList.clear();
            UHfData.dtIndexMap.clear();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UHfData(this);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_jihuo_bt)
    public void onViewClicked() {
        if (etJihuoYwm.getText().toString().trim() == null) {
            ToastUtil.show("请输入一维码!");
            return;
        }
        if (etJihuoYwm.getText().length() < 5) {
            ToastUtil.show("请输入正确的一维码!");
            return;
        }
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
                tvJihuoBt.setText("激活中...");
                showLoadingDialog("激活中...");
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
                    tvJihuoBt.setText("激活");
                    List<IPIDBean> ipidBeans = new ArrayList<>();
                    for (UHfData.InventoryTagMap tagMap : UHfData.lsTagList) {
                        LogUtil.e(tagMap.strEPC + "//" + tagMap.strRSSI + "//" + tagMap.nReadCount);
                        IPIDBean ipidBean = new IPIDBean(tagMap.strEPC, tagMap.nReadCount);
                        ipidBeans.add(ipidBean);
                    }
                    Collections.sort(ipidBeans, new Order());
                    if (ipidBeans != null && ipidBeans.size() > 0) {
                        tvJihuoIfid.setText(ipidBeans.get(0).getIpid());
                        requestJH(ipidBeans.get(0).getIpid(), etJihuoYwm.getText().toString().trim());

                    }

                }
            }
        }, 2500);//3秒后执行Runnable中的run方法
    }

    private void requestJH(String ipid, String trim) {
        OkgoBaseModel bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("goodsRfid", ipid);
        map.put("goodsQrcode", trim);
//        map.put("assetsTypeNo", id);
        bean1OkgoBaseModel.getRequestNetPUT(this, Constant.bind, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                JiHuoBean bean = gson.fromJson(s, JiHuoBean.class);
                if (bean.getCode()==0 && bean.getData() != null ) {
                    ToastUtil.show("激活成功");
                    tvJihuoIfid.setText("");
                    intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra("details", bean.getData());
                    startActivity(intent);
                } else {
                    ToastUtil.show("激活失败" + bean.getMsg());

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


    boolean Scanflag = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UHfData.lsTagList.clear();
        UHfData.dtIndexMap.clear();
    }
}
