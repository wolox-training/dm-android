package ar.com.wolox.android.training.di;

import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.android.training.ui.login.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract LoginActivity exampleActivity();

    @ContributesAndroidInjector
    abstract LoginFragment exampleFragment();
}
