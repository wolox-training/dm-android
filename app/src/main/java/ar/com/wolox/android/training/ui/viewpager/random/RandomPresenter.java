package ar.com.wolox.android.training.ui.viewpager.random;

import ar.com.wolox.android.training.model.User;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import javax.inject.Inject;

public class RandomPresenter extends BasePresenter<IRandomView> {

    public static final String TAG = "RandomPresenter";

    private UserSession mUserSession;
    private User mExampleModel = new User();

    @Inject
    RandomPresenter(UserSession userSession) {
        mUserSession = userSession;
    }
}
