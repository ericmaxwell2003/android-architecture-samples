package com.acme.room.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.acme.room.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TodoEsspressoTest {

    @Rule
    public ActivityTestRule<TodoView> mActivityTestRule = new ActivityTestRule<>(TodoView.class);

    @Before
    public void setup() {
        deleteAllItems();
    }

    @Test
    public void todoEsspressoTest() {

        addTodo("Go to the store");
        addTodo("Pick up the kids");
        addTodo("Clean the apartment");
        waitForSeconds(1);

        checkItem(0);
        checkItem(2);
        waitForSeconds(1);

        deleteChecked();
        waitForSeconds(1);

        assertListSize(1);
        assertTodoItemAt(0, "Pick up the kids");

    }

    private void assertTodoItemAt(int row, String text) {
        ViewInteraction textView = onView(
                allOf(withId(R.id.todo_item_text), withText(text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.todo_list),
                                        row),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(text)));
    }

    private void assertListSize(int expectedCount) {
        onView(withId(R.id.todo_list)).check(new RecyclerViewItemCountAssertion(expectedCount));
    }

    private void setNewTodoText(String text) {
        onView(
            allOf(withId(R.id.add_todo_text),
                    childAtPosition(
                            childAtPosition(
                                    withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                    1),
                            0),
                    isDisplayed()))
            .perform(replaceText(text), closeSoftKeyboard());
    }

    private void clickAddButton() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_item_fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());
    }

    private void addTodo(String text) {
        setNewTodoText(text);
        clickAddButton();
    }

    private void checkItem(int row) {
        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.todo_item_checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.todo_list),
                                        row),
                                0),
                        isDisplayed()));
        appCompatCheckBox.perform(click());
    }


    private void deleteAllItems() {
        checkAll();
        deleteChecked();
    }

    private void checkAll() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Check All"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }

    private void deleteChecked() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Delete Checked"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }

    private void waitForSeconds(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
