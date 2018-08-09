package ar.com.wolox.android.training.ui.login;

import java.util.List;

import ar.com.wolox.android.training.model.User;
import ar.com.wolox.android.training.network.UserService;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<ILoginView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    @Inject
    RetrofitServices mRetrofitServices;

    // Constructor
    @Inject
    public LoginPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public void login(String email, String password) {
        mRetrofitServices.getService(UserService.class).login(email, password).enqueue(new NetworkCallback<List<User>>() {
            @Override
            public void onResponseSuccessful(final List<User> user) {
                if (user.size() > 0) {
                    mUserSession.setEmail(user.get(0).getEmail());
                    getView().onLoginSuccess();
                } else {
                    getView().onLoginError(ErrorCode.INVALID_CREDENTIALS);
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                getView().onLoginError(ErrorCode.CONNECTION_ERROR);
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onLoginError(ErrorCode.CONNECTION_ERROR);
            }
        });
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

    public Boolean isUserLoged() {
        return mUserSession.isLoged();
    }
}