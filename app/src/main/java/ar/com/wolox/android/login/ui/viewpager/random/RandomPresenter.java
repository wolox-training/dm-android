package ar.com.wolox.android.login.ui.viewpager.random;

import static ar.com.wolox.android.login.ui.training.LoginPresenter.NUMBER_MAX;
import static ar.com.wolox.android.login.ui.training.LoginPresenter.NUMBER_MIN;

import android.util.Log;

import ar.com.wolox.android.login.model.ExampleModel;
import ar.com.wolox.android.login.utils.UserSession;
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

    public int generateRandomNumber() {

        // Do some backend logic here, in this case generate just some random number
        // between a given range and update our model
        mExampleModel.someNumber = (new Random().nextInt((NUMBER_MAX - NUMBER_MIN) + 1)) + NUMBER_MIN;
        Log.i(TAG, "A new random number has been generated: " + mExampleModel.someNumber);

        // Notify the view so it can update the UI however it wants to
        getView().onRandomNumberUpdate(mExampleModel.someNumber);

        // Note: Remember that the presenter doesn't know and doesn't care about what the View
        // does with the new value of the random number, it only cares about the backend
        return mExampleModel.someNumber;
    }
}