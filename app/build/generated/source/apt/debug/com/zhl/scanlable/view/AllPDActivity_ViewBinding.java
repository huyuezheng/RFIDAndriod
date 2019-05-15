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

public class AllPDActivity_ViewBinding implements Unbinder {
  private AllPDActivity target;

  private View view2131230826;

  private View view2131230825;

  private View view2131230827;

  @UiThread
  public AllPDActivity_ViewBinding(AllPDActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AllPDActivity_ViewBinding(final AllPDActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_all_cangku, "method 'onViewClicked'");
    view2131230826 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_all_bumen, "method 'onViewClicked'");
    view2131230825 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_all_lsd, "method 'onViewClicked'");
    view2131230827 = view;
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


    view2131230826.setOnClickListener(null);
    view2131230826 = null;
    view2131230825.setOnClickListener(null);
    view2131230825 = null;
    view2131230827.setOnClickListener(null);
    view2131230827 = null;
  }
}
