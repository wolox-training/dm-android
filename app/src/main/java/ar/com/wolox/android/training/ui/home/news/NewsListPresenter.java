package ar.com.wolox.android.training.ui.home.news;

import javax.inject.Inject;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class NewsListPresenter extends BasePresenter<INewsListView> {

    // Constants

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public NewsListPresenter(UserSession userSession) { mUserSession = userSession; }

    public Integer getUserId() {
        return mUserSession.getId();
    }
}
