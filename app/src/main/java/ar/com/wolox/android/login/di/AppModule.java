package ar.com.wolox.android.login.di;

import ar.com.wolox.android.login.ui.training.LoginActivity;
import ar.com.wolox.android.login.ui.training.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract LoginActivity exampleActivity();

    @ContributesAndroidInjector
    abstract LoginFragment exampleFragment();
}
