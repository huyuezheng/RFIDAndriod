// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PDActivity_ViewBinding implements Unbinder {
  private PDActivity target;

  private View view2131230945;

  @UiThread
  public PDActivity_ViewBinding(PDActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PDActivity_ViewBinding(final PDActivity target, View source) {
    this.target = target;

    View view;
    target.etPdId = Utils.findRequiredViewAsType(source, R.id.et_pd_id, "field 'etPdId'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_pd_bt, "method 'onViewClicked'");
    view2131230945 = view;
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
    PDActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etPdId = null;

    view2131230945.setOnClickListener(null);
    view2131230945 = null;
  }
}
