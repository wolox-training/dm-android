package ar.com.wolox.android.training.ui.news;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

public class NewsFormFragment extends WolmoFragment<NewsFormPresenter> implements INewsFormView {

    @Inject
    public NewsFormFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_home_news_form;
    }

    @Override
    public void init() { }
}
