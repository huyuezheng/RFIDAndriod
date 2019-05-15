// Generated code from Butter Knife. Do not modify!
package com.zhl.scanlable.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zhl.scanlable.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GoodsListActivity_ViewBinding implements Unbinder {
  private GoodsListActivity target;

  @UiThread
  public GoodsListActivity_ViewBinding(GoodsListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GoodsListActivity_ViewBinding(GoodsListActivity target, View source) {
    this.target = target;

    target.lvGoodsList = Utils.findRequiredViewAsType(source, R.id.lv_goods_list, "field 'lvGoodsList'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GoodsListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lvGoodsList = null;
  }
}
