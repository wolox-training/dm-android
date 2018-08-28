package ar.com.wolox.android.training.ui.home;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class HomeFragment extends WolmoFragment<HomePresenter> implements IHomeView {

    @Inject
    public HomeFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() { }
}
