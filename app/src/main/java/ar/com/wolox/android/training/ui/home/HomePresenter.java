package ar.com.wolox.android.training.ui.home;

import android.app.Activity;

import javax.inject.Inject;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.android.training.utils.Utils;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class HomePresenter extends BasePresenter<IHomeView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public HomePresenter(UserSession userSession) {
        mUserSession = userSession;
        }

    public void validateLogin(Activity activity) {
        Utils.validateLogin(activity, mUserSession);
    }
}
