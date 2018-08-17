package ar.com.wolox.android.training.ui.news.details;

import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.ui.errors.ErrorCode;

public interface INewsDetailsView {
    void onUpdateLikesSuccess(News news);
    void onUpdateLikesError(ErrorCode errorCode);

    void onRefreshError(ErrorCode errorCode);
    void onRefreshSuccess(News news);
}
