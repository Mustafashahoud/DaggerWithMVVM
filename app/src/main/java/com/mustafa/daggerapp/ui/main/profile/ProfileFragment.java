package com.mustafa.daggerapp.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mustafa.daggerapp.R;
import com.mustafa.daggerapp.models.User;
import com.mustafa.daggerapp.ui.auth.AuthResource;
import com.mustafa.daggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);

        subscribeObservers();
    }

    private void subscribeObservers() {
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;
                        case ERROR:
                            setErrorDetails (userAuthResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        username.setText(data.getUsername());
        website.setText(data.getWebsite());
    }
}
