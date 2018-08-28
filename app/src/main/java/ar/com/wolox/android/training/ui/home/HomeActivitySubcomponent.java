package ar.com.wolox.android.training.ui.home;

import ar.com.wolox.android.training.ui.home.fragment.HomeFragmentModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {HomeFragmentModule.class})
public interface HomeActivitySubcomponent extends AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {
    }
}
