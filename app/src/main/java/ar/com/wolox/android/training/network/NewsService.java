package ar.com.wolox.android.training.network;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.training.model.News;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/news")
    Call<List<News>> list(@Query("_page") int page, @Query("_limit") int limit);

    @PUT("/news/{id}")
    Call<News> updateLikes(@Path("id") int id, @Body News news);

    @GET("/news/{id}")
    Call<News> get(@Path("id") Integer id);
}