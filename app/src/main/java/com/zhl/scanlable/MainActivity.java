package com.zhl.scanlable;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.uhf.scanlable.UHfData.UHfGetData;
import com.zhl.scanlable.base.Constant;
import com.zhl.scanlable.base.ICallListener;
import com.zhl.scanlable.base.OkgoBaseModel;
import com.zhl.scanlable.utils.log.LogUtil;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {

    private String[] tableMenu = {"Scan", "18000-6C"};
    private Intent[] tableIntents;

    private TabHost myTabHost;

    public static final String EXTRA_MODE = "mode";
    public static final String TABLE_SCAN = "Setting";
    public static final String TABLE_18000 = "18000-6C";

    private int MESSAGE_SUCCESS = 0;
    private int MESSAGE_FAIL = 1;
    private String devport = "/dev/ttyMT1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        myTabHost = getTabHost();
        Intent intent0 = new Intent(this, ScanView.class);
        Intent intent1 = new Intent(this, ScanModeGroup.class);
        intent1.putExtra(EXTRA_MODE, TABLE_18000);

        TabHost.TabSpec tabSpec0 = myTabHost.newTabSpec(TABLE_SCAN)
                .setIndicator(TABLE_SCAN).setContent(intent0);
        TabHost.TabSpec tabSpec1 = myTabHost.newTabSpec(TABLE_18000)
                .setIndicator(TABLE_18000).setContent(intent1);

        myTabHost.addTab(tabSpec0);
        myTabHost.addTab(tabSpec1);
        myTabHost.setCurrentTab(1);
//        startService(new Intent(this, StartService.class));
        OkgoBaseModel bean1OkgoBaseModel = OkgoBaseModel.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("barcode", "123456");
        map.put("signalSource", "1213");
        bean1OkgoBaseModel.getRequestNet(this,Constant.save_assetsmanagement, map, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onStart() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int result = UHfGetData.OpenUHf(devport, 57600);
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

    @Override
    protected void onStop() {
        UHfGetData.CloseUHf();
        super.onStop();
    }

    private ConnectHandler mHandler = new ConnectHandler(this);

    private static class ConnectHandler extends Handler {
        private WeakReference<MainActivity> mReference;
        private MainActivity mActivity;

        ConnectHandler(MainActivity activity) {
            mReference = new WeakReference<MainActivity>(activity);
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

}
