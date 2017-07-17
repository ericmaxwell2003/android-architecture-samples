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

package com.example.android.persistence.codelab.step5_solution;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.android.persistence.codelab.realmdb.Loan;
import com.example.android.persistence.codelab.realmdb.utils.DatabaseInitializer;
import com.example.android.persistence.codelab.realmdb.utils.LiveRealmData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.loanModel;


public class CustomResultViewModel extends ViewModel {

    private Realm mDb;
    private LiveData<String> mLoansResult;

    public CustomResultViewModel() {
        mDb = Realm.getDefaultInstance();
        subscribeToMikesLoansSinceYesterday();
        simulateDataUpdates();
    }

    public void simulateDataUpdates() {
        DatabaseInitializer.populateAsync(mDb);
    }

    public LiveData<String> getLoansResult() {
        return mLoansResult;
    }

    private void subscribeToMikesLoansSinceYesterday() {
        LiveRealmData<Loan> loans = loanModel(mDb)
                .findLoansByNameAfter("Mike", getYesterdayDate());
        mLoansResult = Transformations.map(loans, new Function<RealmResults<Loan>, String>() {
            @Override
            public String apply(RealmResults<Loan> loans) {
                StringBuilder sb = new StringBuilder();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                        Locale.US);

                for (Loan loan : loans) {
                    sb.append(String.format("%s\n  (Returned: %s)\n",
                            loan.getBook().getTitle(),
                            simpleDateFormat.format(loan.getEndTime())));
                }
                return sb.toString();
            }
        });
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel... Like RealmResults and the instance of Realm!
     */
    @Override
    protected void onCleared() {
        mDb.close();
        super.onCleared();
    }

    private Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
