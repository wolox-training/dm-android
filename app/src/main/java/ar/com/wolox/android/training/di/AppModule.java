package ar.com.wolox.android.training.di;

import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.home.fragment.HomeFragment;
import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.android.training.ui.login.LoginFragment;

import ar.com.wolox.android.training.ui.signup.SignupActivity;
import ar.com.wolox.android.training.ui.signup.SignupFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    // Login

    @ContributesAndroidInjector
    abstract LoginActivity exampleActivity();

    @ContributesAndroidInjector
    abstract LoginFragment exampleFragment();

    // Signup

    @ContributesAndroidInjector
    abstract SignupActivity signupActivity();

    @ContributesAndroidInjector
    abstract SignupFragment signupFragment();
}
