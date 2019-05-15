// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundHomeActivity_ViewBinding implements Unbinder {
  private FoundHomeActivity target;

  private View view2131230833;

  private View view2131230829;

  private View view2131230830;

  @UiThread
  public FoundHomeActivity_ViewBinding(FoundHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FoundHomeActivity_ViewBinding(final FoundHomeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_home_quyu, "method 'onViewClicked'");
    view2131230833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_dange, "method 'onViewClicked'");
    view2131230829 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_duoge, "method 'onViewClicked'");
    view2131230830 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230833.setOnClickListener(null);
    view2131230833 = null;
    view2131230829.setOnClickListener(null);
    view2131230829 = null;
    view2131230830.setOnClickListener(null);
    view2131230830 = null;
  }
}
