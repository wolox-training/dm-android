package ar.com.wolox.android.training.ui.home.news.list;

import java.util.List;

import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.ui.errors.ErrorCode;

public interface INewsListView {
    void onGetNewsError(ErrorCode errorCode);
    void onGetNewsSuccess(List<News> news);
    void onGetNewsLastPage();
    void onGetLastNewsSuccess(List<News> news);
    void completeLoading();
}
