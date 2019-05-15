// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundActivity_ViewBinding implements Unbinder {
  private FoundActivity target;

  private View view2131230930;

  @UiThread
  public FoundActivity_ViewBinding(FoundActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FoundActivity_ViewBinding(final FoundActivity target, View source) {
    this.target = target;

    View view;
    target.tvFoundIfid = Utils.findRequiredViewAsType(source, R.id.tv_found_ifid, "field 'tvFoundIfid'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_found_bt, "field 'tvFoundBt' and method 'onViewClicked'");
    target.tvFoundBt = Utils.castView(view, R.id.tv_found_bt, "field 'tvFoundBt'", TextView.class);
    view2131230930 = view;
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
    FoundActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFoundIfid = null;
    target.tvFoundBt = null;

    view2131230930.setOnClickListener(null);
    view2131230930 = null;
  }
}
