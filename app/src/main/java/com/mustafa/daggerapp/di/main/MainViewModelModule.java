package com.mustafa.daggerapp.di.main;


import com.mustafa.daggerapp.di.ViewModelKey;
import com.mustafa.daggerapp.ui.auth.AuthViewModel;
import com.mustafa.daggerapp.ui.main.post.PostsViewModel;
import com.mustafa.daggerapp.ui.main.profile.ProfileViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap // cuz i am mapping this dependency to a certain key "multiBinding"
    @ViewModelKey(ProfileViewModel.class) //
    public abstract ViewModel bindProfileViewModelModule(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap // cuz i am mapping this dependency to a certain key "multiBinding"
    @ViewModelKey(PostsViewModel.class) //
    public abstract ViewModel bindPostsViewModelModule(PostsViewModel postsViewModel);
}
