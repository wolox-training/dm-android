package ar.com.wolox.android.training.ui.news.details;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
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

    @BindView(R.id.fragment_news_details_header_image) SimpleDraweeView mImageView;
    @BindView(R.id.fragment_news_details_title) TextView mTitle;
    @BindView(R.id.fragment_news_details_since) TextView mSince;
    @BindView(R.id.fragment_news_details_text) TextView mText;

    @Inject
    public NewsDetailsFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_news_details;
    }

    @Override
    public void init() {
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse("http://static.t13.cl/images/sizes/1200x675/1422252010_riquelme.jpg")).build();
        mImageView.setImageRequest(imageRequest);
        mTitle.setText("Titulo");
        mSince.setText("4h");
        mText.setText(".asdasdsadaskjdsakd sdask dkasjkd asjd asd kjdl as djasd klasd aiojas k");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onNews(News news) {
        Log.d("DylanLog", news.toString());
    }
}