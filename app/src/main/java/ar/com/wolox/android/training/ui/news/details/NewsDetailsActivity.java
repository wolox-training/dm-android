package ar.com.wolox.android.training.ui.news.details;


import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

public class NewsDetailsActivity extends WolmoActivity {

    @Inject
    NewsDetailsFragment mNewsDetailsFragment;
    public static final String NEWS_OBJECT = "ar.com.wolox.android.training.NEWS_OBJECT";

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mNewsDetailsFragment);
    }
}
