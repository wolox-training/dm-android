package ar.com.wolox.android.training.ui.viewpager.random;

import android.util.Log;

import ar.com.wolox.android.training.model.ExampleModel;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import java.util.Random;

import javax.inject.Inject;

public class RandomPresenter extends BasePresenter<IRandomView> {

    public static final String TAG = "RandomPresenter";

    private UserSession mUserSession;
    private ExampleModel mExampleModel = new ExampleModel();

    @Inject
    RandomPresenter(UserSession userSession) {
        mUserSession = userSession;
    }
}
