package ar.com.wolox.android.training.ui.home.news.list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;
import ar.com.wolox.android.training.ui.news.NewsFormActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

public class NewsListFragment extends WolmoFragment<NewsListPresenter> implements INewsListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fragment_news_list_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_news_list_swipe_container) SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.fragment_news_list_fab) FloatingActionButton mFAB;
    @BindView(R.id.fragment_news_list_empty) TextView mEmpty;
    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mNewsAdapter;
    private List<News> mAllNews;

    // Paging
    private final static int PAGE_SIZE = 10;
    private final static int LOAD_BEFORE_N_NEWS = 2;
    private Integer page = 1;
    private Boolean isLoading = false;
    private Boolean isLastPage = false;

    @Inject public NewsListFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_home_news_list;
    }

    @Override
    public void init() {
        // Initializing
        mAllNews = new ArrayList<>();
        Fresco.initialize(getContext());

        // FAB onClick
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToForm();
            }
        });

        // Setting Recycler View
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNewsAdapter = new NewsListAdapter(mAllNews, getPresenter());
        mRecyclerView.setAdapter(mNewsAdapter);

        // Setting Recycler View on scroll hide FAB
        mRecyclerView.addOnScrollListener(onScrollListener);

        // Setting refresh on swipe
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);

        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                startLoading();
                loadNews();
            }
        });
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        int currentScrollPosition = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                if (currentScrollPosition > 100) {
                    mFAB.animate().translationY(getView().getHeight());
                } else {
                    mFAB.animate().translationY(0);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            currentScrollPosition += dy;

            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - LOAD_BEFORE_N_NEWS
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadNews();
                }
            }
        }
    };

    @Override
    public void onRefresh() {
        startLoading();
        getPresenter().getLastNews(mAllNews.size() > 0 ? mAllNews.get(0).getId() : null, PAGE_SIZE);
    }

    public void loadNews() {
        isLoading = true;
        getPresenter().getNews(page, PAGE_SIZE);
        page++;
    }

    private void startLoading() {
        mSwipeLayout.setRefreshing(true);
        isLoading = true;
    }

    public void completeLoading() {
        mSwipeLayout.setRefreshing(false);
        isLoading = false;
    }

    private void showEmptyMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
    }

    private void hideEmptyMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
    }

    private void goToForm() {
        Intent intent = new Intent(getActivity(), NewsFormActivity.class);
        startActivity(intent);
    }

    @Override
    public void onGetNewsError(ErrorCode errorCode) {
        completeLoading();
        switch (errorCode) {
            case NO_NEWS:
                showEmptyMessage();
                break;

            default:
                Toast.makeText(getContext(), ErrorHandler.getErrorMessage(errorCode), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onGetNewsSuccess(List<News> news) {
        mAllNews.addAll(news);
        mNewsAdapter.notifyDataSetChanged();
        hideEmptyMessage();
        completeLoading();
    }

    @Override
    public void onGetNewsLastPage() {
        isLastPage = true;
    }

    @Override
    public void onGetLastNewsSuccess(List<News> news) {
        mAllNews.addAll(0, news);
        mNewsAdapter.notifyDataSetChanged();
        hideEmptyMessage();
        completeLoading();
    }
}
