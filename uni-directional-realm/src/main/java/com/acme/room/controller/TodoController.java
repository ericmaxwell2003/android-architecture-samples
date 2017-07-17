package com.acme.room.controller;

import android.text.TextUtils;

import com.acme.room.model.TodoItem;

import io.realm.Realm;
import io.realm.RealmResults;

public class TodoController {

    private Realm realm;

    public TodoController() {
        this(Realm.getDefaultInstance());
    }

    public TodoController(Realm realm) {
        this.realm = realm;
    }

    public void close() {
        realm.close();
    }

    public void addItem(final String text) {

        if(TextUtils.isEmpty(text)) { return; }

        realm.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(new TodoItem(text));
            }
        });
    }

    public void deleteAllChecked() {

        realm.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm bgRealm) {
                bgRealm.where(TodoItem.class).equalTo("selected", true).findAll().deleteAllFromRealm();
            }
        });

    }

    public void setAllCheckedValue(final boolean isChecked) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm bgRealm) {
                RealmResults<TodoItem> items = bgRealm.where(TodoItem.class).findAll();
                for(TodoItem item : items) { item.setSelected(isChecked); }
            }
        });

    }

    public void setSingleCheckedValue(final String itemId, final boolean isChecked) {

        if(TextUtils.isEmpty(itemId)) { return; }

        realm.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm bgRealm) {
                TodoItem TodoItem = bgRealm.where(TodoItem.class).equalTo("id", itemId).findFirst();
                TodoItem.setSelected(isChecked);
            }
        });
    }

}
