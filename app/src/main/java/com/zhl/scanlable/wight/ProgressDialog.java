package com.zhl.scanlable.wight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhl.scanlable.R;


/**
 * author：   tc
 * date：     2015/10/5 & 14:03
 * version    1.0
 * description 加载中的进度条对话框
 * modify by  ljy
 */
public class ProgressDialog {

    private MaterialDialog mMaterialDialog;//进度对话框
    private final TextView tv_base_dialog_info;
    private final ProgressBar pb_base_dialog;

    public ProgressDialog(Context context) {
        mMaterialDialog = new MaterialDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.include_progress_dialog_layout,
                null);
        tv_base_dialog_info = (TextView) view.findViewById(R.id.tv_base_dialog_info);
        pb_base_dialog = (ProgressBar) view.findViewById(R.id.pb_base_dialog);
        mMaterialDialog.setView(view);
        mMaterialDialog.setBackground(context.getResources().getDrawable(R.color.transparent));
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.setCancelable(true);
    }

    /**
     * 点击返回键是否可以取消进度提示
     *
     * @param isCancelable true为可以，false不可以
     */
    public void setCancelable(boolean isCancelable) {
        mMaterialDialog.setCancelable(isCancelable);
    }

    /**
     * 显示进度条
     */
    public void showDialog(String info) {
        if (info.isEmpty()) {
            pb_base_dialog.setVisibility(View.VISIBLE);
            tv_base_dialog_info.setVisibility(View.GONE);
        } else {
            tv_base_dialog_info.setText(info);
            pb_base_dialog.setVisibility(View.GONE);
            tv_base_dialog_info.setVisibility(View.VISIBLE);
        }
        mMaterialDialog.show();

    }

    /**
     * 隐藏进度条
     */
    public void dismissDialog() {
        mMaterialDialog.dismiss();
    }

}
