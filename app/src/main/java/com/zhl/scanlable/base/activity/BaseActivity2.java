package com.zhl.scanlable.base.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.zhl.scanlable.wight.ShowLoadingDialog;

/**
 * Created by dq on 2018/8/16 0016.
 */

public abstract class BaseActivity2 extends Activity {
    private ShowLoadingDialog progressDialog;
    //    LoadLayout mLoadLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            View contentView = View.inflate(this, getLayoutId(), null);
            setContentView(contentView);
        }
        initView();
        initData();
    }

    public abstract int getLayoutId();

    /**
     * 初始化布局控件
     **/
    protected abstract void initView();

    /*
    * 初始化数据
    * */
    protected abstract void initData();


    /**
     * 显示加载图标
     *
     * @param txt 文字
     */
    public void showAlert(String txt, final boolean isCancel) {
        if (progressDialog == null) {
            progressDialog = new ShowLoadingDialog(this, isCancel);
        }
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    progressDialog.dismiss();
                }
                return false;
            }
        });
        progressDialog.show();
        progressDialog.showText(txt);

    }

    /**
     * 关闭加载图标
     */
    public void dismissAlert() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
