package ar.com.wolox.android.login.network;

import ar.com.wolox.android.login.model.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);
}
