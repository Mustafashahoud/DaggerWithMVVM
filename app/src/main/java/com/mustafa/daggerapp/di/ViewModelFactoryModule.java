package com.mustafa.daggerapp.di;

import com.mustafa.daggerapp.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/*It is responsible for doing the dependency injection for the ViewModel.Factory */
@Module
public abstract class ViewModelFactoryModule {

    //#1 way
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory factory);

    //#2 way
   /* @Provides
    static ViewModelProvider.Factory provideViewModelProviderFactory(ViewModelProviderFactory factory){
        return factory;
    }*/


}
