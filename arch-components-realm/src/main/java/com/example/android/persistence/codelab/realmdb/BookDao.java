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

import android.arch.lifecycle.LiveData;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.asLiveData;

public class BookDao {

    private Realm realm;

    public BookDao(Realm realm) {
        this.realm = realm;
    }

    public Book createOrUpdate(Book book) {
        if (book != null) {
            book = realm.copyToRealmOrUpdate(book);
        }
        return book;
    }



    /**
     *  Additional example custom finder methods.  Unused by the app currently.
     */
    public Book loadBookById(String id) {
        return realm.where(Book.class).equalTo("id", id).findFirst();
    }

    public LiveData<RealmResults<Book>> findBooksBorrowedByName(String userName) {
        return asLiveData(realm
                .where(Book.class)
                .like("loan.user.name", userName)
                .findAll());
    }

    public LiveData<RealmResults<Book>> findBooksBorrowedByNameAfter(String userName, Date after) {
        return asLiveData(realm
                .where(Book.class)
                .like("loan.user.name", userName)
                .greaterThan("loan.endTime", after)
                .findAll());
    }

    public LiveData<RealmResults<Book>> findBooksBorrowedByUser(String userId) {
        return asLiveData(realm
                .where(Book.class)
                .like("loan.user.id", userId)
                .findAll());
    }

    public LiveData<RealmResults<Book>> findBooksBorrowedByUserAfter(String userId, Date after) {
        return asLiveData(realm
                .where(Book.class)
                .like("loan.user.id", userId)
                .greaterThan("loan.endTime", after)
                .findAll());
    }

    public LiveData<RealmResults<Book>> findAllBooks() {
        return asLiveData(realm.where(Book.class).findAll());
    }

}
