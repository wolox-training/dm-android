package ar.com.wolox.android.training.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.news.INewsView;
import ar.com.wolox.android.training.ui.home.news.NewsFragment;
import ar.com.wolox.android.training.ui.home.news.NewsPresenter;
import ar.com.wolox.android.training.ui.home.profile.ProfileFragment;
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

public class HomeFragment extends WolmoFragment<NewsPresenter> implements INewsView {

    @BindView(R.id.fragment_home_pager) ViewPager mViewPager;
    @BindView(R.id.fragment_home_tabs) TabLayout mTabs;

    @Inject SimpleFragmentPagerAdapter mFragmentPagerAdapter;
    @Inject NewsFragment mNewsFragment;
    @Inject ProfileFragment mProfileFragment;

    @Inject
    public HomeFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        mFragmentPagerAdapter.addFragments(
                new Pair<>(mNewsFragment, getString(R.string.tab_news)),
                new Pair<>(mProfileFragment, getString(R.string.tab_profile))
        );

        mViewPager.setAdapter(mFragmentPagerAdapter);

        mTabs.setupWithViewPager(mViewPager);
    }
}
