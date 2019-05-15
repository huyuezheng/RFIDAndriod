package com.zhl.scanlable;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.view.KeyEvent;

/**
 * Created by dq on 2018/8/13 0013.
 */

public class StartService extends Service {

    private BroadcastReceiver keyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //启动应用，参数为需要自动启动的应用的包名
            Intent in = getPackageManager().getLaunchIntentForPackage("com.zhl.scanlable");
            context.startActivity(in );

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //2.创建intent-filter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        filter.addAction("android.rfid.FUN_KEY");
        registerReceiver(keyReceiver, filter);
    }
}
