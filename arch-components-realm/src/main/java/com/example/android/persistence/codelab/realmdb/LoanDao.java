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


import com.example.android.persistence.codelab.realmdb.utils.LiveRealmData;

import java.util.Date;

import io.realm.Realm;

import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.asLiveData;

public class LoanDao  {

    private Realm mRealm;

    public LoanDao(Realm realm) { this.mRealm = realm; }

    public void addLoan(final Date from, final Date to, final String userId, final String bookId) {
        User user = mRealm.where(User.class).equalTo("id", userId).findFirst();
        Book book = mRealm.where(Book.class).equalTo("id", bookId).findFirst();
        Loan loan = new Loan(from, to, book, user);
        mRealm.insert(loan);
    }

    public LiveRealmData<Loan> findLoansByNameAfter(final String userName, final Date after) {
        return asLiveData(mRealm.where(Loan.class)
                .like("user.name", userName)
                .greaterThan("endTime", after)
                .findAllAsync());
    }


    /**
     *  Additional example custom finder methods.  Unused by the app currently.
     */

    public LiveRealmData<Loan> findAllLoans() {
        return asLiveData(mRealm.where(Loan.class).findAllAsync());
    }

}

