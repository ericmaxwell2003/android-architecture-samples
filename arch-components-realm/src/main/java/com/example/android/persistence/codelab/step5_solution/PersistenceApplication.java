package com.example.android.persistence.codelab.step5_solution;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PersistenceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .inMemory()
                        .build());
    }
}
