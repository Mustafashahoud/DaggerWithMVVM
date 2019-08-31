package com.mustafa.daggerapp.di;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mustafa.daggerapp.R;
import com.mustafa.daggerapp.util.Constants;

import javax.inject.Singleton;

import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;
import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {
    // All the App level dependencies for the project like Retrofit Instance, Glide Instance,
    // Any thing that is going to be used for the entire life tome for the application

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    @Singleton
    @Provides
    static RequestOptions provideRequestGlideOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance (Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable (Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }


}
