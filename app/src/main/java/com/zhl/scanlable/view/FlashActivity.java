package com.zhl.scanlable.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhl.scanlable.utils.PreferenceUtil;

/**
 * Created by dq on 2018/8/16 0016.
 */

public class FlashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, HomeActivity.class));
        if (PreferenceUtil.getString(this,"IP","").isEmpty()){
            PreferenceUtil.putString(this,"IP","111.23.244.165");
            PreferenceUtil.putString(this,"DK","8081");
        }

        finish();
    }
}
