package ar.com.wolox.android.training.ui.news.list;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.ui.news.details.NewsDetailsActivity;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private List<News> mDataset;
    private NewsListPresenter mPresenter;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mImage;
        public TextView mTitle;
        public TextView mDescription;
        public TextView mSince;
        public ImageButton mLike;

        public ViewHolder(LinearLayout v) {
            super(v);
            mImage = v.findViewById(R.id.fragment_news_list_item_image);
            mTitle = v.findViewById(R.id.fragment_news_list_item_title);
            mDescription = v.findViewById(R.id.fragment_news_list_item_description);
            mSince = v.findViewById(R.id.fragment_news_list_item_since);
            mLike = v.findViewById(R.id.fragment_news_list_item_like);


            // Setting onClick item
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(v.getContext(), NewsDetailsActivity.class);
                    v.getContext().startActivity(intent);
                    EventBus.getDefault().post(mDataset.get(getAdapterPosition()));
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsListAdapter(List<News> myDataset, NewsListPresenter presenter) {
        mDataset = myDataset;
        mPresenter = presenter;
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mDataset.get(position);
        holder.mTitle.setText(news.getTitle());
        holder.mDescription.setText(news.getText());

        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(news.getPicture())).build();
        holder.mImage.setImageRequest(imageRequest);

        holder.mSince.setText(news.getSince());
        holder.mLike.setImageResource(news.isLikedByUser(mPresenter.getUserId()) ? R.drawable.ic_like_default_on : R.drawable.ic_like_default_off);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
