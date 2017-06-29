import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by asmaa on 6/29/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StringTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testStringValue() {

        onView(withId(R.id.tellJoke)).perform(click());
       // onView(withId(R.id.joke_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.joke_tv)).check(matches(isEditTextValueNotEmpty()));

    }

    Matcher<View> isEditTextValueNotEmpty() {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Match Edit Text Value ");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text =((TextView) view).getText().toString();
                    } else {
                        text =((EditText) view).getText().toString();
                    }

                    return (!text.isEmpty());
                }
                return false;
            }
        };
    }
}