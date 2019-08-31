package com.mustafa.daggerapp.ui.auth;
import com.mustafa.daggerapp.SessionManager;
import com.mustafa.daggerapp.models.User;
import com.mustafa.daggerapp.network.auth.AuthApi;
import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int userId) {
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId (int userId) {
        return LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable){
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                }).map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) {
                        if (user.getId() == -1){
                            return AuthResource.error("could not authenticate ", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })

                .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
