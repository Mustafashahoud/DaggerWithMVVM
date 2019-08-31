package com.mustafa.daggerapp.di;

import android.app.Application;

import com.mustafa.daggerapp.BaseApplication;
import com.mustafa.daggerapp.SessionManager;
import com.mustafa.daggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//Client is BaseApplication and the AppComponent is a server
// In general Components  = Services
// Activities/Fragments = Clients

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }


}
