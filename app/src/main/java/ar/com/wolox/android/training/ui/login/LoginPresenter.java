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
                    mUserSession.setId(user.get(0).getId());
                    getView().onLoginSuccess();
                } else {
                    getView().onLoginError(ErrorCode.INVALID_CREDENTIALS);
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                getView().onLoginError(ErrorCode.INERNET_CONNECTION_ERROR);
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onLoginError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }
}