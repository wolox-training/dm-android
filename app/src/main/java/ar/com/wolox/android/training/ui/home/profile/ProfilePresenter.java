package ar.com.wolox.android.training.ui.home.profile;

import android.app.Activity;

import javax.inject.Inject;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.android.training.utils.Utils;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class ProfilePresenter extends BasePresenter<IProfileView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public ProfilePresenter(UserSession userSession) { }
}
