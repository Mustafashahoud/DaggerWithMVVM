package com.mustafa.daggerapp.di.auth;

import com.mustafa.daggerapp.di.ViewModelKey;
import com.mustafa.daggerapp.ui.auth.AuthViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

// Responsible for providing the AuthViewModel through multi binding
@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap // cuz i am mapping this dependency to a certain key "multiBinding"
    @ViewModelKey(AuthViewModel.class) //
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);


}
