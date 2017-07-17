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

package com.example.android.persistence.codelab.realmdb.utils;

import android.util.Log;

import com.example.android.persistence.codelab.realmdb.Book;
import com.example.android.persistence.codelab.realmdb.User;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.bookModel;
import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.loanModel;
import static com.example.android.persistence.codelab.realmdb.utils.RealmUtils.userModel;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final Realm db) {

        Realm.Transaction task = populateWithTestDataTx;
        db.executeTransactionAsync(task);
    }

    private static void addLoan(final Realm db,
                                final User user, final Book book, Date from, Date to) {
        loanModel(db).addLoan(from, to, user.getId(), book.getId());
        checkpoint(db);
    }

    private static Book addBook(final Realm db, final String id, final String title) {
        Book book = bookModel(db).createOrUpdate(new Book(id, title));
        checkpoint(db);
        return book;
    }

    private static User addUser(final Realm db, final String id, final String name,
                                final String lastName, final int age) {
        User user = userModel(db).createOrUpdate(new User(id, name, lastName, age));
        checkpoint(db);
        return user;
    }

    private static Realm.Transaction populateWithTestDataTx = new Realm.Transaction() {
        @Override
        public void execute(Realm db) {

            db.deleteAll();
            checkpoint(db);

            User user1 = addUser(db, "1", "Jason", "Seaver", 40);
            User user2 = addUser(db, "2", "Mike", "Seaver", 12);
            addUser(db, "3", "Carol", "Seaver", 15);

            Book book1 = addBook(db, "1", "Dune");
            Book book2 = addBook(db, "2", "1984");
            Book book3 = addBook(db, "3", "The War of the Worlds");
            Book book4 = addBook(db, "4", "Brave New World");
            addBook(db, "5", "Foundation");
            try {
                // Loans are added with a delay, to have time for the UI to react to changes.

                Date today = getTodayPlusDays(0);
                Date yesterday = getTodayPlusDays(-1);
                Date twoDaysAgo = getTodayPlusDays(-2);
                Date lastWeek = getTodayPlusDays(-7);
                Date twoWeeksAgo = getTodayPlusDays(-14);

                addLoan(db, user1, book1, twoWeeksAgo, lastWeek);
                Thread.sleep(DELAY_MILLIS);
                addLoan(db, user2, book1, lastWeek, yesterday);
                Thread.sleep(DELAY_MILLIS);
                addLoan(db, user2, book2, lastWeek, today);
                Thread.sleep(DELAY_MILLIS);
                addLoan(db, user2, book3, lastWeek, twoDaysAgo);
                Thread.sleep(DELAY_MILLIS);
                addLoan(db, user2, book4, lastWeek, today);
                Log.d("DB", "Added loans");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

    /**
     * Commit the current transaction and start a new one.
     * This works well for this demo to simulate new updates streaming into
     * realm.  Not recommended for a real world app.
     */
    private static void checkpoint(Realm db) {
        db.commitTransaction();
        db.beginTransaction();
    }

}
