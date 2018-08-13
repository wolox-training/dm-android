package ar.com.wolox.android.training.ui.home.news;

import android.app.Activity;

import javax.inject.Inject;

import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.android.training.utils.Utils;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class NewsPresenter extends BasePresenter<INewsView> {

    // Constants

    // Variables

    // Constructor
    @Inject
    public NewsPresenter(UserSession userSession) { }
}
