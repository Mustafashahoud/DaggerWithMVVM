package com.mustafa.daggerapp.network.main;

import com.mustafa.daggerapp.models.Post;
import com.mustafa.daggerapp.models.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// posts?userId=1
// @Path appends a Slash
// @Query appends a ?
public interface MainApi {

    @GET("posts")
    Flowable<List<Post>> getPostsFromUser( @Query("userId") int userId );
}
