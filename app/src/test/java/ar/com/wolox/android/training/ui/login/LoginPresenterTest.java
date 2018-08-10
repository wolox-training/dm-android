package ar.com.wolox.android.training.ui.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.wolox.android.training.model.User;
import ar.com.wolox.android.training.network.UserService;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class LoginPresenterTest {

    private ILoginView mLoginView;
    private LoginPresenter mLoginPresenter;
    private RetrofitServices mRetrofitServices;
    private UserSession mUserSession;

    private Call<List<User>> mUserCall;
    private User mUser;
    private List<User> mUsers;

    @Before
    @SuppressWarnings("unchecked")
    public void createInstances() {
        mLoginView = mock(ILoginView.class);
        mRetrofitServices = mock(RetrofitServices.class);
        mUserCall = mock(Call.class);
        mUser = mock(User.class);
        mUsers = Arrays.asList(mUser);
        mUserSession = mock(UserSession.class);

        when(mUser.getEmail()).thenReturn("email@example.com");

        when(mRetrofitServices.getService(any(Class.class))).thenReturn(new UserService() {
            @Override
            public Call<List<User>> login(@Query("email") String email, @Query("password") String password) {
                return mUserCall;
            }
        });

        mLoginPresenter = new LoginPresenter(mUserSession);
        // Simulate dagger member injection
        mLoginPresenter.mRetrofitServices = mRetrofitServices;
        mLoginPresenter.attachView(mLoginView);
    }

    /**
     * NOTE: This is only an example of more complex mocks using Mockito.
     * You shouldn't mock inner workings of the classes. You can use "MockWebServer":
     * https://github.com/square/okhttp/tree/master/mockwebserver
     */
    @Test
    @SuppressWarnings("unchecked")
    public void callViewOnSuccess() {
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback<List<User>>) invocation.getArguments()[0])
                        .onResponse(mUserCall, Response.success(mUsers));
                return null;
            }
        }).when(mUserCall).enqueue(any(Callback.class));

        mLoginPresenter.login("email@example.com", "1234");
        // Verify view updates
        verify(mLoginView, times(1)).onLoginSuccess();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void storeSessionOnSuccess() {
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback<List<User>>) invocation.getArguments()[0])
                        .onResponse(mUserCall, Response.success(mUsers));
                return null;
            }
        }).when(mUserCall).enqueue(any(Callback.class));

        mLoginPresenter.login("email@example.com", "1234");
        // Verify view updates
        verify(mUserSession, times(1)).setEmail(matches("email@example.com"));
    }
}
