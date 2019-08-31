package com.mustafa.daggerapp.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.mustafa.daggerapp.R;
import com.mustafa.daggerapp.models.User;
import com.mustafa.daggerapp.ui.main.MainActivity;
import com.mustafa.daggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel mAuthViewModel;

    private EditText userId;

    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuthViewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        userId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);

        subscribeObservers();
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button : {
                attempLogin();
                break;
            }
        }
    }

    private void attempLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }
        mAuthViewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

    private void subscribeObservers() {
        mAuthViewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status){
                        case LOADING:{
                            showProgressbar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            showProgressbar(false);
                            Log.d(TAG, "onChanged: Login Success" + userAuthResource.data.getEmail());
                            onLoginSuccess();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            showProgressbar(false);
                            break;
                        }
                        case ERROR:{
                            Toast.makeText(AuthActivity.this, userAuthResource.message + "\nDid you enter a number between 1 and 10?"
                                    , Toast.LENGTH_SHORT).show();
                            showProgressbar(false);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void onLoginSuccess () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressbar (boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
