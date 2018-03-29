package com.ebelli.auctions

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ebelli.auctions.app.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by enzobelli on 29/03/2018.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testOnDisplayList(){
        onView(withId(R.id.activity_main_auctions)).check(matches(isDisplayed()))

    }
}