// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsActivity_ViewBinding implements Unbinder {
  private DetailsActivity target;

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target, View source) {
    this.target = target;

    target.tvTm = Utils.findRequiredViewAsType(source, R.id.tv_tm, "field 'tvTm'", TextView.class);
    target.tvMc = Utils.findRequiredViewAsType(source, R.id.tv_mc, "field 'tvMc'", TextView.class);
    target.tvGg = Utils.findRequiredViewAsType(source, R.id.tv_gg, "field 'tvGg'", TextView.class);
    target.tvQy = Utils.findRequiredViewAsType(source, R.id.tv_qy, "field 'tvQy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTm = null;
    target.tvMc = null;
    target.tvGg = null;
    target.tvQy = null;
  }
}
