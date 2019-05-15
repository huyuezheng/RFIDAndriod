// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view2131230951;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    target.etIp = Utils.findRequiredViewAsType(source, R.id.et_ip, "field 'etIp'", EditText.class);
    target.etDk = Utils.findRequiredViewAsType(source, R.id.et_dk, "field 'etDk'", EditText.class);
    target.etSbh = Utils.findRequiredViewAsType(source, R.id.et_sbh, "field 'etSbh'", EditText.class);
    target.tvTm = Utils.findRequiredViewAsType(source, R.id.tv_tm, "field 'tvTm'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_setting_bt, "field 'tvSettingBt' and method 'onViewClicked'");
    target.tvSettingBt = Utils.castView(view, R.id.tv_setting_bt, "field 'tvSettingBt'", TextView.class);
    view2131230951 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etIp = null;
    target.etDk = null;
    target.etSbh = null;
    target.tvTm = null;
    target.tvSettingBt = null;

    view2131230951.setOnClickListener(null);
    view2131230951 = null;
  }
}
