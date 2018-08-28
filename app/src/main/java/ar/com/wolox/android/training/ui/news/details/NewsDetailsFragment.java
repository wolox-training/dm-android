package ar.com.wolox.android.training.ui.news.details;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.model.NewsLikeEvent;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;
import ar.com.wolox.android.training.ui.fullscreen_image.FullScreenImageActivity;
import ar.com.wolox.android.training.ui.fullscreen_image.FullScreenImageFragment;
import ar.com.wolox.android.training.ui.views.ToolbarView;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class NewsDetailsFragment extends WolmoFragment<NewsDetailsPresenter> implements INewsDetailsView, SwipeRefreshLayout.OnRefreshListener {

    // Constants
    public static final String NEWS_OBJECT = "ar.com.wolox.android.training.NEWS_OBJECT";

    // Variables
    @BindView(R.id.fragment_news_details_header_image)
    SimpleDraweeView mImageView;
    @BindView(R.id.fragment_news_details_title)
    TextView mTitle;
    @BindView(R.id.fragment_news_details_since)
    TextView mSince;
    @BindView(R.id.fragment_news_details_text)
    TextView mText;
    @BindView(R.id.fragment_news_details_like)
    ImageView mLike;
    @BindView(R.id.fragment_news_details_toolbar)
    ToolbarView mToolbar;
    @BindView(R.id.fragment_news_details_swipe_layout)
    SwipeRefreshLayout mSwipeLayout;
    private News mNews;
    private Integer mUserLogedId;

    // Constructor
    @Inject
    public NewsDetailsFragment() {
    }

    @Override
    public int layout() {
        return R.layout.fragment_news_details;
    }

    @Override
    public void init() {
        if (!getActivity().getIntent().hasExtra(NEWS_OBJECT)) {
            Toast.makeText(getContext(), R.string.error_unexpected, Toast.LENGTH_LONG).show();
            return;
        }

        // Init
        setToolbar();
        mUserLogedId = getPresenter().getUserId();
        mNews = (News) getActivity().getIntent().getSerializableExtra(NEWS_OBJECT);
        refreshNews();

        // Onclick image => fullscreen
        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FullScreenImageActivity.class);
            intent.putExtra(FullScreenImageFragment.KEY_PICTURE, mNews.getPicture());
            startActivity(intent);
        });

        // Setting refresh on swipe
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
    }

    private void setToolbar() {
        mToolbar.setTitle(R.string.news_details_toolbar_title);
        mToolbar.setTitleColor(R.color.white);
        mToolbar.setImage(R.drawable.ic_back);
        mToolbar.setImageColorFilter(R.color.white);
        mToolbar.setOnImageClick(v -> getActivity().finish());
    }

    private void refreshNews() {
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mNews.getPicture())).build();
        mImageView.setImageRequest(imageRequest);
        mTitle.setText(mNews.getTitle());
        mSince.setText(mNews.getSince());
        mText.setText(mNews.getText());
        mLike.setImageResource(getNewsLikeRes());
        mLike.setOnClickListener(v -> onLikeClick());
    }

    private int getNewsLikeRes() {
        return mNews.isLikedByUser(mUserLogedId) ? R.drawable.ic_like_on : R.drawable.ic_like_off;
    }

    public void onLikeClick() {
        toogleUserLike();
        getPresenter().updateLikes(mNews);
    }

    private void toogleUserLike() {
        if (mNews.isLikedByUser(mUserLogedId)) {
            mNews.userDislike(mUserLogedId);
        } else {
            mNews.userLike(mUserLogedId);
        }
    }

    @Override
    public void onUpdateLikesSuccess(News news) {
        mNews = news;
        mLike.setImageResource(getNewsLikeRes());

        EventBus.getDefault().post(new NewsLikeEvent(mUserLogedId, mNews.getId(), mNews.isLikedByUser(mUserLogedId)));
    }

    @Override
    public void onUpdateLikesError(ErrorCode errorCode) {
        toogleUserLike();
        mLike.setImageResource(getNewsLikeRes());
        Toast.makeText(getContext(), ErrorHandler.getErrorMessage(getContext(), errorCode), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        startLoading();
        getPresenter().refreshNews(mNews.getId());
    }

    @Override
    public void onRefreshError(ErrorCode errorCode) {
        completeLoading();
        Toast.makeText(getContext(), ErrorHandler.getErrorMessage(getContext(), errorCode), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefreshSuccess(News news) {
        completeLoading();
        mNews = news;
        refreshNews();
    }

    private void startLoading() {
        mSwipeLayout.setRefreshing(true);
    }

    private void completeLoading() {
        mSwipeLayout.setRefreshing(false);
    }
}