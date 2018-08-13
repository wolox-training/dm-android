package ar.com.wolox.android.training.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ar.com.wolox.android.training.ui.home.HomeActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = HomeFragmentSubComponent.class)
public abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeActivityFactory(
            HomeFragmentSubComponent.Builder builder);

    @Provides
    static FragmentManager providesFragmentManager(HomeActivity homeActivity) {
        return homeActivity.getSupportFragmentManager();
    }

}
