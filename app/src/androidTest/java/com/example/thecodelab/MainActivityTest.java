package com.example.thecodelab;

import android.os.SystemClock;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.thecodelab.R;
import com.example.thecodelab.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private CountingIdlingResource countingIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void registerIdlingResource(){
        countingIdlingResource = mActivityTestRule.getActivity().getIdlingResourceInTest();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @Test
    public void loadMore_shouldPass() throws Exception {
        SystemClock.sleep(8000);
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(10, click()));
    }

    @Test
    public void swipeToRefreshShown() throws Exception{
        SystemClock.sleep(8000);
        onView(withId(R.id.swiperefresh)).perform(swipeDown());
    }



    @After
    public void unregisterIdlingResource(){
        if (countingIdlingResource != null){
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }
}