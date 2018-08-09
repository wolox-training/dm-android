package ar.com.wolox.android.training.ui.login;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<ILoginView> {

    // Constants

    // Variables
    private UserSession mUserSession;
    private Boolean userLoged;

    // Constructor
    @Inject
    public LoginPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public void login(String email, String password) {
        mUserSession.setEmail(email);
        getView().onLoginSuccess();
    }

    public String getUserEmail() {
        return mUserSession.getEmail();
    }
}