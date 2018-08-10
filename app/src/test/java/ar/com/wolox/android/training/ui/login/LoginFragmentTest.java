package ar.com.wolox.android.training.ui.login;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {

    private LoginFragment mLoginFragment;
    private LoginActivity mLoginActivity;
    private TextView mEmail;
    private TextView mPassword;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Before
    @SuppressWarnings("unchecked")
    public void createInstances() {
    }

    /**
     * NOTE: This is only an example of more complex mocks using Mockito.
     * You shouldn't mock inner workings of the classes. You can use "MockWebServer":
     * https://github.com/square/okhttp/tree/master/mockwebserver
     */
    @Test
    @SuppressWarnings("unchecked")
    public void setErrorEmptyEmail() {
        onView(withId(R.id.fragment_login_login)).perform(click());
        onView(withId(R.id.fragment_login_email))
                .check(matches(hasErrorText(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS))));
    }
}
