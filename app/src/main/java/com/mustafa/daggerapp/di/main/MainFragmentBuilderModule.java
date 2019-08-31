package com.mustafa.daggerapp.di.main;

import com.mustafa.daggerapp.ui.main.post.PostsFragment;
import com.mustafa.daggerapp.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector()
    abstract PostsFragment contributePostsFragment();
}
