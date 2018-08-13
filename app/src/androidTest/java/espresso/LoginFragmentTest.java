package espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.TrainingApplication;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;
import ar.com.wolox.android.training.ui.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    @SuppressWarnings("unchecked")
    public void setErrorEmptyEmail() {
        onView(withId(R.id.fragment_login_login)).perform(click());
        onView(withId(R.id.fragment_login_email))
                .check(matches(hasErrorText(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS))));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void setErrorEmptyPassword() {
        onView(withId(R.id.fragment_login_email)).perform(typeText("email"));
        onView(withId(R.id.fragment_login_login)).perform(click());
        onView(withId(R.id.fragment_login_password))
                .check(matches(hasErrorText(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS))));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void setErrorWrongEmail() {
        onView(withId(R.id.fragment_login_email)).perform(typeText("email"));
        onView(withId(R.id.fragment_login_password)).perform(typeText("1234"));
        onView(withId(R.id.fragment_login_login)).perform(click());
        onView(withId(R.id.fragment_login_password))
                .check(matches(hasErrorText(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS))));
    }
}
