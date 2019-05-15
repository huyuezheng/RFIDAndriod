package com.zhl.scanlable.base.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.lzy.okgo.OkGo;
import com.zhl.scanlable.R;
import com.zhl.scanlable.wight.loadlayout.LoadLayout;

import butterknife.ButterKnife;


/**
 * author:  ljy
 * date:    2017/9/13
 * description:
 * 不含ToolBar、加载布局（正文，加载中，加载失败，无数据）的activity基类
 * 子类不需要再绑定ButterKnife
 * 实现setContentLayout来设置布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 * <p>
 * http://www.jianshu.com/p/3d9ee98a9570
 */
public abstract class AppBaseActivity extends BaseActivity {

    LoadLayout mLoadLayout;//加载布局，可以显示各种状态的布局, 如加载中，加载成功, 加载失败, 无数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutResId) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.setContentView(R.layout.activity_base_app);

        addViewToContainer(layoutResId);
//        init();
    }


    //将布局加入到LoadLayout中
    public void addViewToContainer(int layoutResId) {
        mLoadLayout = (LoadLayout) findViewById(R.id.base_content_layout_app);
        View view = getLayoutInflater().inflate(layoutResId, null);
        mLoadLayout.removeAllViews();
        mLoadLayout.addSuccessView(view);
        ButterKnife.bind(this);

    }


    /**
     * 获取加载布局，从而设置各种加载状态
     */
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLoadLayout != null) {
            mLoadLayout.closeAnim();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
