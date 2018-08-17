package ar.com.wolox.android.training.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.widget.Toolbar;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.news.list.NewsListFragment;
import ar.com.wolox.android.training.ui.home.profile.ProfileFragment;
import ar.com.wolox.android.training.ui.views.ToolbarView;
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

public class HomeFragment extends WolmoFragment {

    @BindView(R.id.fragment_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.fragment_home_tabs)
    TabLayout mTabs;
    @BindView(R.id.fragment_home_toolbar)
    ToolbarView mToolbar;

    @Inject
    SimpleFragmentPagerAdapter mFragmentPagerAdapter;
    @Inject
    NewsListFragment mNewsFragment;
    @Inject
    ProfileFragment mProfileFragment;

    @Inject
    public HomeFragment() {
    }

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
        mTabs.getTabAt(0).setIcon(R.drawable.ic_news_list);
        mTabs.getTabAt(1).setIcon(R.drawable.ic_profile);

        // Setting toolbar
        mToolbar.setTitle(R.string.page_home_toolbar_title);
        mToolbar.setImage(R.drawable.wolox_logo);
    }
}
