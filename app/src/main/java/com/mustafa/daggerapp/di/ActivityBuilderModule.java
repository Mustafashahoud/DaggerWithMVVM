package com.mustafa.daggerapp.di;

import com.mustafa.daggerapp.di.auth.AuthModule;
import com.mustafa.daggerapp.di.auth.AuthViewModelModule;
import com.mustafa.daggerapp.di.main.MainFragmentBuilderModule;
import com.mustafa.daggerapp.di.main.MainModule;
import com.mustafa.daggerapp.di.main.MainViewModelModule;
import com.mustafa.daggerapp.ui.auth.AuthActivity;
import com.mustafa.daggerapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

// here is the sub component
@Module
public abstract class ActivityBuilderModule {

    // here put all the activity that you have in the app for each one a @ContributesAndroidInjector should be declared
    @ContributesAndroidInjector(modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector (modules = {MainFragmentBuilderModule.class, MainViewModelModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity ();

}
