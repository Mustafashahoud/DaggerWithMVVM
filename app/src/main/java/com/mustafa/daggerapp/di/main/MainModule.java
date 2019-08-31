package com.mustafa.daggerapp.di.main;

import com.mustafa.daggerapp.network.main.MainApi;
import com.mustafa.daggerapp.ui.main.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {
    // we have retrofit dependency cuz it's in the AppModule which is in the AppComponent and the we are now in the subComponent
    // We are in a sub component cuz this Module AuthModule is inside ActivityBuilderModule inside ContributesAndroidInjector

    @Provides
    static PostRecyclerAdapter providePostRecyclerAdapter() {
        return new PostRecyclerAdapter();
    }
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
