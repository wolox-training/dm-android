package ar.com.wolox.android.training.ui.home.fragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface HomeFragmentSubComponent extends AndroidInjector<HomeFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeFragment> {
    }
}
