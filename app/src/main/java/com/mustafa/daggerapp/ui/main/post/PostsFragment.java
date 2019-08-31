package com.mustafa.daggerapp.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mustafa.daggerapp.R;
import com.mustafa.daggerapp.models.Post;
import com.mustafa.daggerapp.ui.main.Resource;
import com.mustafa.daggerapp.util.VerticalSpaceItemDecoration;
import com.mustafa.daggerapp.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {


    private static final String TAG = "PostsFragment";
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initPostRecyclerAdapter();
        subscribeObservers();
    }

    private void subscribeObservers () {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {

                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING: {
                            Log.d(TAG, "onChanged: LOADING...");
                            break;

                        }

                        case ERROR: {
                            Log.d(TAG, "onChanged: ERROR...");
                            break;

                        }

                        case SUCCESS: {
                            Log.d(TAG, "onChanged: got posts...");
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;

                        }
                    }
                }
            }
        });
    }

    private void initPostRecyclerAdapter() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postRecyclerAdapter);

    }
}
