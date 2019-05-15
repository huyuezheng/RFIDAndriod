// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PDDetailActivity_ViewBinding implements Unbinder {
  private PDDetailActivity target;

  @UiThread
  public PDDetailActivity_ViewBinding(PDDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PDDetailActivity_ViewBinding(PDDetailActivity target, View source) {
    this.target = target;

    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PDDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvList = null;
  }
}
