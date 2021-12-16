package co.id.egiwibowo.todoapps.test

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import co.id.egiwibowo.todoapps.MainActivity
import co.id.egiwibowo.todoapps.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun add_todo() {


        var rv: RecyclerView? = null

        activityRule.scenario.onActivity {
            rv = it.findViewById<RecyclerView>(R.id.rv_todos)
        }

        val count = rv?.adapter?.itemCount

        Log.d("HAI", count.toString())

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.et_title))
            .perform(typeText("title"), closeSoftKeyboard())

        onView(withId(R.id.et_descriptions))
            .perform(typeText("descriptions"), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).perform(click())


        activityRule.scenario.onActivity {
            rv = it.findViewById<RecyclerView>(R.id.rv_todos)
        }

        val count2 = rv?.adapter?.itemCount
        Log.d("HAI JUGA", count2.toString())
    }
}