// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JihuoActivity_ViewBinding implements Unbinder {
  private JihuoActivity target;

  private View view2131230935;

  @UiThread
  public JihuoActivity_ViewBinding(JihuoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public JihuoActivity_ViewBinding(final JihuoActivity target, View source) {
    this.target = target;

    View view;
    target.tvJihuoIfid = Utils.findRequiredViewAsType(source, R.id.tv_jihuo_ifid, "field 'tvJihuoIfid'", TextView.class);
    target.spiner = Utils.findRequiredViewAsType(source, R.id.spiner, "field 'spiner'", Spinner.class);
    target.etJihuoYwm = Utils.findRequiredViewAsType(source, R.id.et_jihuo_ywm, "field 'etJihuoYwm'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_jihuo_bt, "field 'tvJihuoBt' and method 'onViewClicked'");
    target.tvJihuoBt = Utils.castView(view, R.id.tv_jihuo_bt, "field 'tvJihuoBt'", TextView.class);
    view2131230935 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.tvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tvNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    JihuoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvJihuoIfid = null;
    target.spiner = null;
    target.etJihuoYwm = null;
    target.tvJihuoBt = null;
    target.tvNum = null;

    view2131230935.setOnClickListener(null);
    view2131230935 = null;
  }
}
