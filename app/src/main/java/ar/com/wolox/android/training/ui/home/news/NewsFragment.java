package ar.com.wolox.android.training.ui.home.news;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class NewsFragment extends WolmoFragment<NewsPresenter> implements INewsView {

    @Inject public NewsFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_home_news;
    }

    @Override
    public void init() { }

}
