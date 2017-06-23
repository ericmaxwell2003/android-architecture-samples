// Generated code from Butter Knife. Do not modify!
package com.acme.realmudi.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.acme.realmudi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TodoView_ViewBinding implements Unbinder {
  private TodoView target;

  private View view2131558523;

  @UiThread
  public TodoView_ViewBinding(TodoView target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TodoView_ViewBinding(final TodoView target, View source) {
    this.target = target;

    View view;
    target.setAddTodoText(Utils.findRequiredViewAsType(source, R.id.add_todo_text, "field 'addTodoText'", EditText.class));
    target.setRecyclerView(Utils.findRequiredViewAsType(source, R.id.todo_list, "field 'recyclerView'", RecyclerView.class));
    view = Utils.findRequiredView(source, R.id.add_item_fab, "field 'addItemFab' and method 'onAddItem'");
    target.setAddItemFab(Utils.castView(view, R.id.add_item_fab, "field 'addItemFab'", FloatingActionButton.class));
    view2131558523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddItem();
      }
    });
    target.setToolbar(Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    TodoView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.setAddTodoText(null);
    target.setRecyclerView(null);
    target.setAddItemFab(null);
    target.setToolbar(null);

    view2131558523.setOnClickListener(null);
    view2131558523 = null;
  }
}
