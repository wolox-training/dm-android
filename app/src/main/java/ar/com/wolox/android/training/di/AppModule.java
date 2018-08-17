package ar.com.wolox.android.training.di;

import ar.com.wolox.android.training.ui.fullscreen_image.FullScreenImageActivity;
import ar.com.wolox.android.training.ui.fullscreen_image.FullScreenImageFragment;
import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.android.training.ui.login.LoginFragment;

import ar.com.wolox.android.training.ui.news.details.NewsDetailsActivity;
import ar.com.wolox.android.training.ui.news.details.NewsDetailsFragment;
import ar.com.wolox.android.training.ui.news.form.NewsFormActivity;
import ar.com.wolox.android.training.ui.news.form.NewsFormFragment;
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

    // News form

    @ContributesAndroidInjector
    abstract NewsFormActivity newsFormActivity();

    @ContributesAndroidInjector
    abstract NewsFormFragment newsFormFragment();

    // News details

    @ContributesAndroidInjector
    abstract NewsDetailsActivity newsDetailsActivity();

    @ContributesAndroidInjector
    abstract NewsDetailsFragment newsDetailsFragment();

    // Fullscreen image

    @ContributesAndroidInjector
    abstract FullScreenImageActivity fullScreenImageActivity();

    @ContributesAndroidInjector
    abstract FullScreenImageFragment fullScreenImageFragment();
}
