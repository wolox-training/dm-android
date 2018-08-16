package ar.com.wolox.android.training.ui.news.details;

import javax.inject.Inject;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class NewsDetailsPresenter extends BasePresenter<INewsDetailsView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public NewsDetailsPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public Integer getUserId() {
        return mUserSession.getId();
    }
}
