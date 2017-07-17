/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.codelab.realmdb;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserDao {

    private Realm realm;

    public UserDao(Realm realm) { this.realm = realm; }

    public User createOrUpdate(User user) {
        if (user != null) {
            user = realm.copyToRealmOrUpdate(user);
        }
        return user;
    }

    /**
     *  Additional example custom finder methods.  Unused by the app currently.
     */
    public RealmResults<User> loadAllUsers() {
        return realm.where(User.class).findAll();
    }

    public User loadUserById(String id) {
        return realm.where(User.class).equalTo("id", id).findFirst();
    }

    public RealmResults<User> findByNameAndLastName(String firstName, String lastName) {
        return realm.where(User.class)
                .equalTo("name", firstName)
                .equalTo("lastName", lastName)
                .findAll();
    }
}