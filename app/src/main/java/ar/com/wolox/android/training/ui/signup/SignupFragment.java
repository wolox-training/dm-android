package ar.com.wolox.android.training.ui.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class SignupFragment extends WolmoFragment<SignupPresenter> implements ISignupView {

    @Inject
    public SignupFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void init() { }
}
