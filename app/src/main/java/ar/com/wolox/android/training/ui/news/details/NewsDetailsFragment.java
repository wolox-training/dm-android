package ar.com.wolox.android.training.ui.news.details;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.model.News;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

public class NewsDetailsFragment extends WolmoFragment<NewsDetailsPresenter> implements INewsDetailsView {

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

    @Inject
    public NewsDetailsFragment() {
    }

    @Override
    public int layout() {
        return R.layout.fragment_news_details;
    }

    @Override
    public void init() {
        if (!getActivity().getIntent().hasExtra(NewsDetailsActivity.NEWS_OBJECT)) {
            Toast.makeText(getContext(), R.string.error_unexpected, Toast.LENGTH_LONG).show();
            return;
        }

        setNews((News) getActivity().getIntent().getSerializableExtra(NewsDetailsActivity.NEWS_OBJECT));
    }

    private void setNews(News news) {
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(news.getPicture())).build();
        mImageView.setImageRequest(imageRequest);
        mTitle.setText(news.getTitle());
        mSince.setText(news.getSince());
        mText.setText(news.getText());
        mLike.setImageResource(news.isLikedByUser(getPresenter().getUserId()) ? R.drawable.ic_like_default_on : R.drawable.ic_like_default_off);
    }
}