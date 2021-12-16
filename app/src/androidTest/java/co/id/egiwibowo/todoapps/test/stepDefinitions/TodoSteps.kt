package co.id.egiwibowo.todoapps.test.stepDefinitions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import co.id.egiwibowo.todoapps.MainActivity
import co.id.egiwibowo.todoapps.R
import co.id.egiwibowo.todoapps.test.utils.RecyclerViewItemCountAssertion
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Rule
import org.junit.runner.RunWith
import java.util.concurrent.RecursiveAction

fun clickOnViewChild(viewId: Int) = object : ViewAction {
    override fun getConstraints() = null

    override fun getDescription() = "Click on a child view with specified id."

    override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
}

@RunWith(AndroidJUnit4ClassRunner::class)
class TodoSteps {

    private var initiateCount = 0
    private var expectedCount = 0

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityRule = ActivityScenarioRule(MainActivity::class.java)
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Given("I'm in Screen Create Todo")
    fun im_in_screen_create_todo() {
        activityRule.scenario.onActivity {
            initiateCount = it.findViewById<RecyclerView>(R.id.rv_todos).adapter?.itemCount ?: 0
        }
        onView(withId(R.id.fab)).perform(ViewActions.click())
    }

    @When("I type title and description todo")
    fun i_type_title_and_desc_todo() {
        onView(withId(R.id.et_title))
            .perform(ViewActions.typeText("title"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.et_descriptions))
            .perform(ViewActions.typeText("descriptions"), ViewActions.closeSoftKeyboard())
    }

    @When("Click Save Button")
    fun click_save_btn() {
        onView(withId(R.id.btn_save)).perform(ViewActions.click())
    }

    @Then("Back to List Todo and todo nambah 1")
    fun show_create_todo_app_screen1() {
        onView(withId(R.id.rv_todos)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_todos)).check(RecyclerViewItemCountAssertion(initiateCount + 1));
    }

    @Then("Back to List Todo")
    fun show_create_todo_app_screen() {
        onView(withId(R.id.rv_todos)).check(matches(isDisplayed()))
    }

    @Given("I'm in Screen Edit Todo")
    fun im_in_screen_edit_todo() {
        onView(withId(R.id.rv_todos)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

    }

    @When("I edit title and description todo")
    fun i_edit_title_and_desc_todo() {
        onView(withId(R.id.et_title))
            .perform(ViewActions.typeText("title edited"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.et_descriptions))
            .perform(ViewActions.typeText("descriptions edited"), ViewActions.closeSoftKeyboard())
    }
}