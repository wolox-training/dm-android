package ar.com.wolox.android.training.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.login.LoginActivity;
import ar.com.wolox.android.training.ui.login.LoginFragment;
import ar.com.wolox.android.training.utils.Extras;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

public class RootActivity extends WolmoActivity {

    @Inject
    RootFragment mRootFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mRootFragment);
    }
}
