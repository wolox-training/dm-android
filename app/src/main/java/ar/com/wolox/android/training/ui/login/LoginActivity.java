package ar.com.wolox.android.training.ui.login;


import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

import javax.inject.Inject;

public class LoginActivity extends WolmoActivity {

    @Inject
    LoginFragment mLoginFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mLoginFragment);
    }
}
