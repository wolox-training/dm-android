package ar.com.wolox.android.training.ui.news.details;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.network.NewsService;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;

public class NewsDetailsPresenter extends BasePresenter<INewsDetailsView> {

    // Constants

    // Variables
    private UserSession mUserSession;
    @Inject
    RetrofitServices mRetrofitServices;

    // Constructor
    @Inject
    public NewsDetailsPresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public Integer getUserId() {
        return mUserSession.getId();
    }

    public void updateLikes(News news) {
        mRetrofitServices.getService(NewsService.class).updateLikes(news.getId(), news).enqueue(new NetworkCallback<News>() {
            @Override
            public void onResponseSuccessful(@Nullable News news) {
                getView().onUpdateLikesSuccess(news);
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int i) {
                getView().onUpdateLikesError(ErrorCode.INERNET_CONNECTION_ERROR);
            }

            @Override
            public void onCallFailure(Throwable throwable) {
                getView().onUpdateLikesError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }

    public void refreshNews(Integer id) {
        mRetrofitServices.getService(NewsService.class).get(id).enqueue(new NetworkCallback<News>() {
            @Override
            public void onResponseSuccessful(@Nullable News news) {
                getView().onRefreshSuccess(news);
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int i) {
                getView().onRefreshError(ErrorCode.INERNET_CONNECTION_ERROR);
            }

            @Override
            public void onCallFailure(Throwable throwable) {
                getView().onRefreshError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }
}
