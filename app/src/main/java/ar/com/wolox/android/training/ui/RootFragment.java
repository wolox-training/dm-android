package ar.com.wolox.android.training.ui;

import android.content.Intent;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class RootFragment extends WolmoFragment<RootPresenter> {

    @Inject
    public RootFragment() {}

    @Override
    public int layout() {
        return R.layout.activity_base;
    }

    @Override
    public void init() {
        Intent intent;
        if (getPresenter().isUserLogged()) {
            intent = new Intent(getActivity(), HomeActivity.class);
        } else {
            intent = new Intent(getActivity(), LoginActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}
