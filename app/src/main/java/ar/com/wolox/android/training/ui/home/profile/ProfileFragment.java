package ar.com.wolox.android.training.ui.home.profile;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.profile.IProfileView;
import ar.com.wolox.android.training.ui.home.profile.ProfilePresenter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class ProfileFragment extends WolmoFragment<ProfilePresenter> implements IProfileView {

    @Inject public ProfileFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_home_profile;
    }

    @Override
    public void init() { }
}
