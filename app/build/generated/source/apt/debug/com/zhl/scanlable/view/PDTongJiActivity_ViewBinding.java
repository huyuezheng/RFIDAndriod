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

public class PDTongJiActivity_ViewBinding implements Unbinder {
  private PDTongJiActivity target;

  private View view2131230953;

  @UiThread
  public PDTongJiActivity_ViewBinding(PDTongJiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PDTongJiActivity_ViewBinding(final PDTongJiActivity target, View source) {
    this.target = target;

    View view;
    target.tvPdqy = Utils.findRequiredViewAsType(source, R.id.tv_pdqy, "field 'tvPdqy'", TextView.class);
    target.tvPdzclx = Utils.findRequiredViewAsType(source, R.id.tv_pdzclx, "field 'tvPdzclx'", TextView.class);
    target.tvPdsl = Utils.findRequiredViewAsType(source, R.id.tv_pdsl, "field 'tvPdsl'", TextView.class);
    target.tvSjsl = Utils.findRequiredViewAsType(source, R.id.tv_sjsl, "field 'tvSjsl'", TextView.class);
    target.etPdr = Utils.findRequiredViewAsType(source, R.id.et_pdr, "field 'etPdr'", EditText.class);
    target.etBz = Utils.findRequiredViewAsType(source, R.id.et_bz, "field 'etBz'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_tijiao, "method 'onViewClicked'");
    view2131230953 = view;
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
    PDTongJiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPdqy = null;
    target.tvPdzclx = null;
    target.tvPdsl = null;
    target.tvSjsl = null;
    target.etPdr = null;
    target.etBz = null;

    view2131230953.setOnClickListener(null);
    view2131230953 = null;
  }
}
