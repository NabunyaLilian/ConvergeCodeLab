package com.example.thecodelab;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.view.DetailActivity;
import com.example.thecodelab.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {
    private CountingIdlingResource mcountingIdlingResource;
    private final String userName = "k33ptoo";

    @Rule
    public IntentsTestRule<DetailActivity>
            mActivityTestRule = new IntentsTestRule<>(DetailActivity.class, true, false);

    public void registerIdlingResource(){
        mcountingIdlingResource = mActivityTestRule.getActivity().getIdlingResourceInTest();
        IdlingRegistry.getInstance().register(mcountingIdlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        if (mcountingIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mcountingIdlingResource);
        }
    }

    @Before
    public void stubUserName(){
        GithubUser githubUsers = new GithubUser(userName, "", "", "",
                "", 0,0,0);
        Intent intent = new Intent();
        intent.putExtra("image_name", userName);
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void userScreeenDisplayed() {
        SystemClock.sleep(8000);
        onView(withId(R.id.image_description)).check(matches(withText(userName)));
    }


}