package ar.com.wolox.android.training.ui.news.list;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.network.NewsService;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;

public class NewsListPresenter extends BasePresenter<INewsListView> {

    // Constants

    // Variables
    private UserSession mUserSession;
    @Inject RetrofitServices mRetrofitServices;

    // Constructor
    @Inject
    public NewsListPresenter(UserSession userSession) { mUserSession = userSession; }

    public Integer getUserId() {
        return mUserSession.getId();
    }

    public void getNews(int page, int limit) {

        mRetrofitServices.getService(NewsService.class).list(page, limit).enqueue(new NetworkCallback<List<News>>() {
            @Override
            public void onResponseSuccessful(List<News> news) {
                if (news.size() > 0) {
                    getView().onGetNewsSuccess(news);

                    if (news.size() < limit) {
                        getView().onGetNewsLastPage();
                    }
                } else {
                    getView().onGetNewsError(ErrorCode.NO_NEWS);
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                switch (code) {
                    case 400:
                        getView().onGetNewsLastPage();
                        break;

                    default:
                        getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
                        break;
                }
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }

    public void getLastNews(Integer idLast, Integer limit) {
        mRetrofitServices.getService(NewsService.class).list(1, limit).enqueue(new NetworkCallback<List<News>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponseSuccessful(List<News> news) {
                if (news.stream().anyMatch(n -> n.getId().equals(idLast))) {
                    news = news.subList(0,  news.indexOf(news.stream().filter(n -> n.getId().equals(idLast)).findFirst().get()));
                }

                if (news.size() > 0) {
                    getView().onGetLastNewsSuccess(news);
                } else {
                    getView().completeLoading();
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                switch (code) {
                    case 400:
                        getView().completeLoading();
                        break;

                    default:
                        getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
                        break;
                }
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }
}
