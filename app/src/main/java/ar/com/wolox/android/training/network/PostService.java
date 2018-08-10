package ar.com.wolox.android.training.network;

import ar.com.wolox.android.training.model.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);
}