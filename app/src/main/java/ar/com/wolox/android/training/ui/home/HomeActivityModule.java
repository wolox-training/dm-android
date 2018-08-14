package ar.com.wolox.android.training.ui.home;

import android.app.Activity;

import ar.com.wolox.android.training.ui.home.news.NewsListFragment;
import ar.com.wolox.android.training.ui.home.profile.ProfileFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = HomeActivitySubcomponent.class)
public abstract class HomeActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivityFactory(HomeActivitySubcomponent.Builder builder);

    @ContributesAndroidInjector
    abstract NewsListFragment newsFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

}
