package com.mustafa.daggerapp.ui.main.profile;

import android.util.Log;

import com.mustafa.daggerapp.SessionManager;
import com.mustafa.daggerapp.models.User;
import com.mustafa.daggerapp.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import dagger.Reusable;

public class ProfileViewModel extends ViewModel {

     private static final String TAG = "ProfileViewModel";

     // We will get the same sessionManager object
     private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();

    }
}
