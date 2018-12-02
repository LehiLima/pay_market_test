package com.example.mercadolivretest


import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@LargeTest
@RunWith(AndroidJUnit4::class)
class PayMarketCompleteFlowTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun payMarketAmount_paymentMethod() {
        val appCompatEditText = onView(
            allOf(withId(R.id.amount_value), childAtPosition(childAtPosition(withId(R.id.container), 0), 1), isDisplayed())
        )
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard())
        val appCompatButton = onView(allOf(withId(R.id.btn_next), withText("Next"),
            childAtPosition(childAtPosition(withId(R.id.container)
            , 0), 2), isDisplayed()))
        appCompatButton.perform(click())
        sleep(200)
        onView(withId(R.id.payment_method_list_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun payMarket_paymentmethod_cardissuers() {

        val appCompatEditText = onView(
            allOf(withId(R.id.amount_value), childAtPosition(childAtPosition(withId(R.id.container), 0), 1), isDisplayed())
        )
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard())
        val appCompatButton = onView(allOf(withId(R.id.btn_next), withText("Next"),
            childAtPosition(childAtPosition(withId(R.id.container)
                , 0), 2), isDisplayed()))
        appCompatButton.perform(click())

        sleep(200)

        onView(withId(R.id.payment_method_list_rv)).check(matches(isDisplayed()))

         val recyclerView = onView( allOf(
            withId(R.id.payment_method_list_rv),
            childAtPosition(
                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                1
            )
         )
         )
         recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

         sleep(100)

         onView(withId(R.id.card_issuers_list_rv)).check(matches(isDisplayed()))

    }


    @Test
    fun payMarket_cardissuers_installments() {
        val appCompatEditText = onView(
            allOf(withId(R.id.amount_value), childAtPosition(childAtPosition(withId(R.id.container), 0), 1), isDisplayed())
        )
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard())
        val appCompatButton = onView(allOf(withId(R.id.btn_next), withText("Next"),
            childAtPosition(childAtPosition(withId(R.id.container)
                , 0), 2), isDisplayed()))
        appCompatButton.perform(click())

        sleep(200)

        onView(withId(R.id.payment_method_list_rv)).check(matches(isDisplayed()))

        val recyclerView = onView( allOf(
            withId(R.id.payment_method_list_rv),
            childAtPosition(
                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                1
            )
        )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        sleep(100)

        onView(withId(R.id.card_issuers_list_rv)).check(matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.card_issuers_list_rv),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        sleep(100)

        onView(withId(R.id.installments_list_rv)).check(matches(isDisplayed()))

    }

    @Test
    fun payMarket_installments_amount_finish() {
        val appCompatEditText = onView(
            allOf(withId(R.id.amount_value), childAtPosition(childAtPosition(withId(R.id.container), 0), 1), isDisplayed())
        )
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard())
        val appCompatButton = onView(allOf(withId(R.id.btn_next), withText("Next"),
            childAtPosition(childAtPosition(withId(R.id.container)
                , 0), 2), isDisplayed()))
        appCompatButton.perform(click())

        sleep(200)

        onView(withId(R.id.payment_method_list_rv)).check(matches(isDisplayed()))

        val recyclerView = onView( allOf(
            withId(R.id.payment_method_list_rv),
            childAtPosition(
                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                1
            )
        )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        sleep(100)

        onView(withId(R.id.card_issuers_list_rv)).check(matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.card_issuers_list_rv),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        sleep(100)

        onView(withId(R.id.installments_list_rv)).check(matches(isDisplayed()))


        val recyclerView3 = onView(
            allOf(
                withId(R.id.installments_list_rv),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        sleep(200)

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_no), withText("Não"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        6
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        onView(withId(R.id.amount_value)).check(matches(isDisplayed()))


    }



    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
