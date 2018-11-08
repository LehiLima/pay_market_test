package com.example.mercadolivretest;
import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import junit.framework.Assert;
import okhttp3.mockwebserver.MockWebServer;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by lehi.teixeira on 08/11/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PayMarketTest {
    //TODO: unfinished

    private MockWebServer mockServer;

    @Rule
    public ActivityTestRule<MainActivity> mTasksActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Mock
    private Context context;

    @Before
    public void resetState() {
       // mockServer = TestUtils.getServer();
    }

    @Test
    public void payMarket_ShowPropertyScreen() {
        onView(withId(R.id.amount_value)).perform(clearText(), click(), typeText("4000"), closeSoftKeyboard());
        Assert.assertEquals("400","400");
        onView(withId(R.id.btn_next)).perform(click());
        sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.payment_method_list_rv)).atPosition(1).perform(click());
        sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.card_issuers_list_rv)).atPosition(1).perform(click());
        sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.installments_list_rv)).atPosition(1).perform(click());
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
