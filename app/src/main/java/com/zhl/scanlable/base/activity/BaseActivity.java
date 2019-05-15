package com.zhl.scanlable.base.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;


import com.zhl.scanlable.R;
import com.zhl.scanlable.utils.log.LogUtil;
import com.zhl.scanlable.wight.ProgressDialog;

import org.zackratos.ultimatebar.UltimateBar;


/**
 * date：      2017/9/13
 * version     1.0
 * description: Activity的基类，包含Activity栈管理，状态栏/导航栏颜色设置，销毁时取消网络请求等
 * 子类需要进行ButterKnife绑定
 * <p>
 * http://www.jianshu.com/p/3d9ee98a9570
 */

public abstract class BaseActivity extends AbstractActivity implements IBaseActivity {

    //"加载中"的弹窗
    private ProgressDialog mProgressDialog;
    //状态栏导航栏颜色工具类
    private UltimateBar ultimateBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        setContentLayout();//由具体的activity实现，设置内容布局ID
        initBarColor();//初始化状态栏/导航栏颜色，需在设置了布局后再调用
        initView();//由具体的activity实现，做视图相关的初始化
        obtainData();//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化


    }


    private void initBarColor() {
        int color = getResourceColor(R.color.yellow);

        setBarColor(color, 0, color, 0);
    }


    public UltimateBar getUltimateBar() {
        if (ultimateBar == null) {
            ultimateBar = new UltimateBar(this);
        }
        return ultimateBar;
    }

    //设置状态栏、导航栏颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setBarColor(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setColorBar(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置状态栏的颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarColor(int color, int alpha) {
        getUltimateBar().setColorStatusBar(color, alpha);
    }

    //设置状态栏、导航栏颜色（有DrawerLayout时可使用这种），第二个参数控制透明度，布局内容不占据状态栏空间
    public void setBarColorForDrawer(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setColorBarForDrawer(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置状态栏的颜色（有DrawerLayout时可使用这种），第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarColorForDrawer(int color, int alpha) {
        getUltimateBar().setColorBarForDrawer(color, alpha);
    }

    //设置半透明的状态栏、导航栏颜色，第二个参数控制透明度，布局内容占据状态栏空间
    public void setBarTranslucent(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setTransparentBar(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置半透明的状态栏颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarTranslucent(int color, int alpha) {
        getUltimateBar().setColorBarForDrawer(color, alpha);
    }

    //设置全透明的状态栏、导航栏颜色，布局内容占据状态栏空间，参数为是否也应用到
    public void setTransparentBar(boolean applyNav) {
        getUltimateBar().setImmersionBar(applyNav);
    }

    //隐藏状态栏、导航栏，布局内容占据状态栏导航栏空间，参数为是否也应用到导航栏上
    public void hideBar(boolean applyNav) {
        getUltimateBar().setHideBar(applyNav);
    }


    /**
     * 显示圆形进度对话框
     * 传""是默认小圆圈,传字符串就会显示
     */
    public void showLoadingDialog(String info) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.showDialog(info);
    }

    /**
     * 显示圆形进度对话框（不可关闭）
     */
    public void showNoCancelLoadingDialog(String info) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.showDialog(info);
        mProgressDialog.setCancelable(false);
    }

    /**
     * 关闭进度对话框
     */
    public void dismissLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.dismissDialog();
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    @Override
    public String getResourceString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }

    @Override
    public void onLowMemory() {
        LogUtil.e("内存不足");
        //清空图片内存缓存（包括Bitmap缓存和未解码图片的缓存）
        super.onLowMemory();
    }


    public OnKeyClickListener mOnKeyClickListener;

    public void setOnKeyListener(OnKeyClickListener onKeyClickListener) {
        this.mOnKeyClickListener = onKeyClickListener;
    }

    /**
     * 按键的监听，供页面设置自定义的按键行为
     */
    public interface OnKeyClickListener {
        /**
         * 点击了返回键
         */
        void clickBack();

        //可加入其它按键事件
    }

}
