package ar.com.wolox.android.training.ui.news;


import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.signup.SignupFragment;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

public class NewsFormActivity extends WolmoActivity {

    @Inject
    NewsFormFragment mNewsFormFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mNewsFormFragment);
    }
}
