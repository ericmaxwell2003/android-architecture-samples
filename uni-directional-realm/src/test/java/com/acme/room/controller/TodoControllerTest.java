package com.acme.room.controller;

import com.acme.room.model.TodoItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.realm.BuildConfig;
import io.realm.Realm;
import io.realm.log.RealmLog;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyNew;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest({Realm.class, RealmLog.class, TodoItem.class})
public class TodoControllerTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    Realm mockRealm;
    TodoController controller;

    @Before
    public void setup() {
        mockStatic(RealmLog.class);
        mockStatic(Realm.class);

        Realm mockRealm = PowerMockito.mock(Realm.class);

        when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        this.mockRealm = mockRealm;
        controller = new TodoController(mockRealm);
    }

    @Test
    public void shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), is(mockRealm));
    }

    @Test
    public void testAddItem() throws Exception {

        final String todoItemText = "Walk the dog";
        final ArgumentCaptor<TodoItem> itemCaptor = ArgumentCaptor.forClass(TodoItem.class);
        final ArgumentCaptor<Realm.Transaction> txCaptor = ArgumentCaptor.forClass(Realm.Transaction.class);

        controller.addItem(todoItemText);

        // Verify executeTransactionAsync is called, then artificially execute it
        // with the mock realmRealm implementation.
        verify(mockRealm, times(1)).executeTransactionAsync(txCaptor.capture());
        txCaptor.getValue().execute(mockRealm);

        // Verify that Realm#createObject was called only once
        // Verify that a TodoItem was in fact created.
        verify(mockRealm, times(1)).insertOrUpdate(itemCaptor.capture());

        // With the value we expect.
        assertEquals(todoItemText, itemCaptor.getValue().getText());
    }

    @Test
    public void testAddItem_NullValue() throws Exception {

        final String todoItemText = null;

        doCallRealMethod().when(mockRealm).executeTransactionAsync(Mockito.any(Realm.Transaction.class));

        TodoItem todoItem = mock(TodoItem.class);
        when(mockRealm.createObject(TodoItem.class)).thenReturn(todoItem);

        controller.addItem(todoItemText);

        // Verify that Realm#createObject is never called.
        verify(mockRealm, never()).createObject(TodoItem.class);

        // Verify that close is not called.
        verify(mockRealm, never()).close();
    }

}
