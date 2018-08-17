package ar.com.wolox.android.training.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.android.training.ui.signup.ISignupView;
import ar.com.wolox.android.training.ui.signup.SignupPresenter;
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
        if (getPresenter().isUserLoged()) {
            intent = new Intent(getActivity(), HomeActivity.class);
        } else {
            intent = new Intent(getActivity(), LoginActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}
