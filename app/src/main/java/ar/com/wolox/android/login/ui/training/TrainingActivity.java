package ar.com.wolox.android.login.ui.training;


import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

import javax.inject.Inject;

public class TrainingActivity extends WolmoActivity {

    @Inject
    TrainingFragment mTrainingFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mTrainingFragment);
    }
}
