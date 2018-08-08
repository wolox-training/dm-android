package ar.com.wolox.android.login.di;

import ar.com.wolox.android.login.ui.training.TrainingActivity;
import ar.com.wolox.android.login.ui.training.TrainingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract TrainingActivity exampleActivity();

    @ContributesAndroidInjector
    abstract TrainingFragment exampleFragment();
}
