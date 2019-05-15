// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  private View view2131230831;

  private View view2131230832;

  private View view2131230834;

  private View view2131230828;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(final HomeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_home_jihou, "field 'llHomeJihou' and method 'onViewClicked'");
    target.llHomeJihou = Utils.castView(view, R.id.ll_home_jihou, "field 'llHomeJihou'", LinearLayout.class);
    view2131230831 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_pandian, "field 'llHomePandian' and method 'onViewClicked'");
    target.llHomePandian = Utils.castView(view, R.id.ll_home_pandian, "field 'llHomePandian'", LinearLayout.class);
    view2131230832 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_shezhi, "field 'llHomeShezhi' and method 'onViewClicked'");
    target.llHomeShezhi = Utils.castView(view, R.id.ll_home_shezhi, "field 'llHomeShezhi'", LinearLayout.class);
    view2131230834 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_chaxun, "field 'llHomeChaxun' and method 'onViewClicked'");
    target.llHomeChaxun = Utils.castView(view, R.id.ll_home_chaxun, "field 'llHomeChaxun'", LinearLayout.class);
    view2131230828 = view;
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
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llHomeJihou = null;
    target.llHomePandian = null;
    target.llHomeShezhi = null;
    target.llHomeChaxun = null;

    view2131230831.setOnClickListener(null);
    view2131230831 = null;
    view2131230832.setOnClickListener(null);
    view2131230832 = null;
    view2131230834.setOnClickListener(null);
    view2131230834 = null;
    view2131230828.setOnClickListener(null);
    view2131230828 = null;
  }
}
