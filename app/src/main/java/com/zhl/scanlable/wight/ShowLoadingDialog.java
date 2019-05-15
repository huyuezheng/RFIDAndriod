package com.zhl.scanlable.wight;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zhl.scanlable.R;


/**
 * @author zhangwenquan
 * @package com.zhl.huiqu.traffic0.widget
 * @fileName ShowLoadingDialog
 * @date on 2018/3/30 15:01
 * @email 845882168@qq.com
 * @describe
 */

public class ShowLoadingDialog extends Dialog {

    private TextView tInfo;

    public ShowLoadingDialog(Context context, boolean isCancel) {
        super(context, R.style.loading_dialog);
        setCanceledOnTouchOutside(false);
        if (!isCancel) {
            setCancelable(false);
        }
        View v = View.inflate(context, R.layout.traffic_loading_dialog, null);
        tInfo = (TextView) v.findViewById(R.id.info);
        setContentView(v);
    }

    public void showText(String txt) {
        if (TextUtils.isEmpty(txt)) {
            tInfo.setVisibility(View.GONE);
        } else {
            tInfo.setText(txt);
            tInfo.setVisibility(View.VISIBLE);
        }
    }
}
