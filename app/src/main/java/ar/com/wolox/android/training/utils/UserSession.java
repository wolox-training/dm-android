package ar.com.wolox.android.training.utils;

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager;

import javax.inject.Inject;

@ApplicationScope
public class UserSession {

    private SharedPreferencesManager mSharedPreferencesManager;
    private String mEmail;

    @Inject
    public UserSession(SharedPreferencesManager sharedPreferencesManager) {
        mSharedPreferencesManager = sharedPreferencesManager;
    }

    public void setEmail(String email) {
        mEmail = email;
        mSharedPreferencesManager.store(Extras.UserLogin.EMAIL, email);
    }

    public String getEmail() {
        // Really, we don't need to query the username because this instance live as long as the
        // application, but we should add a check in case Android decides to kill the application
        // and return to a state where this isn't initialized.
        if (mEmail == null) {
            mEmail = mSharedPreferencesManager.get(Extras.UserLogin.EMAIL, null);
        }
        return mEmail;
    }

    public boolean isLoged() {
        return getEmail() != null;
    }
}
