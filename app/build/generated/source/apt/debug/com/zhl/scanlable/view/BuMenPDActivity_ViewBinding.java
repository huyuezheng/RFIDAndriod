// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BuMenPDActivity_ViewBinding implements Unbinder {
  private BuMenPDActivity target;

  private View view2131230940;

  private View view2131230941;

  private View view2131230959;

  private View view2131230928;

  private View view2131230943;

  private View view2131230942;

  @UiThread
  public BuMenPDActivity_ViewBinding(BuMenPDActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BuMenPDActivity_ViewBinding(final BuMenPDActivity target, View source) {
    this.target = target;

    View view;
    target.listView = Utils.findRequiredViewAsType(source, R.id.lv_pandian_list, "field 'listView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_pandian_bt, "field 'tvPandianBt' and method 'onViewClicked'");
    target.tvPandianBt = Utils.castView(view, R.id.tv_pandian_bt, "field 'tvPandianBt'", TextView.class);
    view2131230940 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.txNum = Utils.findRequiredViewAsType(source, R.id.tv_znum, "field 'txNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_pandian_cx, "field 'tvPandianCx' and method 'onViewClicked'");
    target.tvPandianCx = Utils.castView(view, R.id.tv_pandian_cx, "field 'tvPandianCx'", TextView.class);
    view2131230941 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zclx, "field 'tvZclx' and method 'onViewClicked'");
    target.tvZclx = Utils.castView(view, R.id.tv_zclx, "field 'tvZclx'", TextView.class);
    view2131230959 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ck, "field 'tvCk' and method 'onViewClicked'");
    target.tvCk = Utils.castView(view, R.id.tv_ck, "field 'tvCk'", TextView.class);
    view2131230928 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvKczs = Utils.findRequiredViewAsType(source, R.id.tv_kczs, "field 'tvKczs'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_pandian_pd, "field 'tvPandianPd' and method 'onViewClicked'");
    target.tvPandianPd = Utils.castView(view, R.id.tv_pandian_pd, "field 'tvPandianPd'", TextView.class);
    view2131230943 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pandian_cxkc, "method 'onViewClicked'");
    view2131230942 = view;
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
    BuMenPDActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
    target.tvPandianBt = null;
    target.txNum = null;
    target.tvPandianCx = null;
    target.tvZclx = null;
    target.tvCk = null;
    target.tvKczs = null;
    target.tvPandianPd = null;

    view2131230940.setOnClickListener(null);
    view2131230940 = null;
    view2131230941.setOnClickListener(null);
    view2131230941 = null;
    view2131230959.setOnClickListener(null);
    view2131230959 = null;
    view2131230928.setOnClickListener(null);
    view2131230928 = null;
    view2131230943.setOnClickListener(null);
    view2131230943 = null;
    view2131230942.setOnClickListener(null);
    view2131230942 = null;
  }
}
