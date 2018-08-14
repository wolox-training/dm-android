package ar.com.wolox.android.training.network;

import ar.com.wolox.android.training.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    @GET("/posts/{id}")
    Call<News> getPostById(@Path("id") int id);
}