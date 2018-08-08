package ar.com.wolox.android.training.utils;

import android.util.Log;

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager;

import javax.inject.Inject;

@ApplicationScope
public class UserSession {

    private SharedPreferencesManager mSharedPreferencesManager;
    private String mEmail;
    private String mPassword;

    @Inject
    public UserSession(SharedPreferencesManager sharedPreferencesManager) {
        mSharedPreferencesManager = sharedPreferencesManager;
    }

    private void setEmail(String email) {
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

    private void setPassword(String password) {
        mPassword = password;
        mSharedPreferencesManager.store(Extras.UserLogin.PASSWORD, password);
    }

    public String getPassword() {
        // Really, we don't need to query the username because this instance live as long as the
        // application, but we should add a check in case Android decides to kill the application
        // and return to a state where this isn't initialized.
        if (mPassword == null) {
            mPassword = mSharedPreferencesManager.get(Extras.UserLogin.PASSWORD, null);
        }
        return mPassword;
    }

    public void setUser(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public Boolean isLoged() {
        return getEmail() != null && getPassword() != null;
    }
}
