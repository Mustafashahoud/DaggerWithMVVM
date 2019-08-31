package com.mustafa.daggerapp.di.auth;

import com.mustafa.daggerapp.SessionManager;
import com.mustafa.daggerapp.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AuthModule {

    // we have retrofit dependency cuz it's in the AppModule which is in the AppComponent and the we are now in the subComponent
    // We are in a subcomponent cuz this Module AuthModule is inside ActivityBuilderModule inside ContributesAndroidInjector

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
