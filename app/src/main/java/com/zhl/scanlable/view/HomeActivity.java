package com.zhl.scanlable.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uhf.scanlable.UHfData;
import com.zhl.scanlable.MainActivity;
import com.zhl.scanlable.R;
import com.zhl.scanlable.base.activity.AppBaseActivity;
import com.zhl.scanlable.utils.log.LogUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dq on 2018/8/16 0016.
 */

public class HomeActivity extends AppBaseActivity {


    @BindView(R.id.ll_home_jihou)
    LinearLayout llHomeJihou;
    @BindView(R.id.ll_home_pandian)
    LinearLayout llHomePandian;
    @BindView(R.id.ll_home_shezhi)
    LinearLayout llHomeShezhi;
    @BindView(R.id.ll_home_chaxun)
    LinearLayout llHomeChaxun;
    private Intent intent;

    @Override
    protected void setContentLayout() {
        new UHfData(this);
        setContentView(R.layout.activity_home);
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

    private int MESSAGE_SUCCESS = 0;
    private int MESSAGE_FAIL = 1;
    private String devport = "/dev/ttyMT1";

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                try {
                    //开启扫描
                    int result = UHfData.UHfGetData.OpenUHf(devport, 57600);
                    if (result == 0) {
                        mHandler.sendEmptyMessage(MESSAGE_SUCCESS);
                    } else {
                        mHandler.sendEmptyMessage(MESSAGE_FAIL);
                    }
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(MESSAGE_FAIL);
                }
            }
        }).start();
        super.onStart();
    }
    /*@Override
    protected void onStop() {
        UHfData.UHfGetData.CloseUHf();
        super.onStop();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭扫描
        UHfData.UHfGetData.CloseUHf();
    }

    private ConnectHandler mHandler = new ConnectHandler(this);

    private static class ConnectHandler extends Handler {
        private WeakReference<HomeActivity> mReference;
        private HomeActivity mActivity;

        ConnectHandler(HomeActivity activity) {
            mReference = new WeakReference<HomeActivity>(activity);
            mActivity = mReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == mActivity.MESSAGE_SUCCESS) {
                Toast.makeText(mActivity.getApplicationContext(), mActivity.getString(R.string.port_connect_success),
                        Toast.LENGTH_SHORT).show();
            } else if (msg.what == mActivity.MESSAGE_FAIL) {
                Toast.makeText(mActivity.getApplicationContext(), mActivity.getString(R.string.port_connect_fail),
                        Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    }


    @OnClick({R.id.ll_home_jihou, R.id.ll_home_chaxun, R.id.ll_home_pandian, R.id.ll_home_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_jihou:
                intent = new Intent(getContext(), JihuoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_chaxun:
             /*   intent = new Intent(getContext(), FoundActivity.class);
                startActivity(intent);*/
                intent = new Intent(getContext(),FoundHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_pandian:
                intent = new Intent(getContext(),AllPDActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_shezhi:
                intent = new Intent(getContext(),SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
