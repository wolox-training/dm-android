package ar.com.wolox.android.training.ui;

import javax.inject.Inject;

import ar.com.wolox.android.training.model.User;
import ar.com.wolox.android.training.ui.signup.ISignupView;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class RootPresenter extends BasePresenter<ISignupView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public RootPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public Boolean isUserLoged() {
        return mUserSession.getEmail() != null;
    }
}
