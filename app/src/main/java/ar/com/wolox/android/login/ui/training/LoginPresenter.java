package ar.com.wolox.android.login.ui.training;

import ar.com.wolox.android.login.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<ILoginView> {

    // Constants
    public static final String TAG = "LoginPresenter";
    public static final int NUMBER_MAX = 1000;
    public static final int NUMBER_MIN = 200;

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public LoginPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public void login(String email, String password) {
        Boolean validEmail = this.validateEmailField(email);
        Boolean validPassword = this.validatePasswordField(password);

        if (!validEmail || !validPassword) {
            return;
        }

        mUserSession.setUser(email, password);
        getView().onLoginSuccess();
    }

    public String getEmail() {
        return mUserSession.getEmail();
    }

    public String getPassword() {
        return mUserSession.getPassword();
    }

    private Boolean validateEmailField(String email) {
        if (!this.validateEmptyField(email)) {
            getView().onLoginEmailError("El campo es obligatorio");
            return false;
        } else if (!this.validateEmail(email)) {
            getView().onLoginEmailError("El email es incorrecto");
            return false;
        }

        return true;
    }

    public Boolean validatePasswordField(String password) {
        if (!this.validateEmptyField(password)) {
            getView().onLoginPasswordError("El campo es obligatorio");
            return false;
        }

        return true;
    }

    private Boolean validateEmptyField(String field) {
        return field.length() > 0;
    }

    private Boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
