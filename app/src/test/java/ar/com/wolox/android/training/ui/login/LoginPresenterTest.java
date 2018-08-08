package ar.com.wolox.android.training.ui.login;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.com.wolox.android.training.utils.UserSession;

import org.junit.Before;
import org.junit.Test;

public class LoginPresenterTest {

    private ILoginView mExampleView;
    private LoginPresenter mLoginPresenter;
    private UserSession mUserSession;

    @Before
    public void createInstances() {
        mExampleView = mock(ILoginView.class);
        mUserSession = mock(UserSession.class);
        mLoginPresenter = new LoginPresenter(mUserSession);
    }

    @Test
    public void usernameIsStored() {
        mLoginPresenter.attachView(mExampleView);
        mLoginPresenter.storeUsername("Test");
        verify(mUserSession, times(1)).setUsername("Test");
    }

    @Test
    public void storeUsernameUpdatesView() {
        mLoginPresenter.attachView(mExampleView);
        mLoginPresenter.storeUsername("Test");
        verify(mExampleView, times(1)).onUsernameSaved();
    }

}
