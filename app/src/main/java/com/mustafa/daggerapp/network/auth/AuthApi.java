package com.mustafa.daggerapp.network.auth;

import com.mustafa.daggerapp.models.User;


import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );
}

/**
 * We need a way to convert the Call object to Flowable rxJava objects
 * using implementation 'com.squareup.retrofit2:adapter-rxjava:latest.version'
 */
