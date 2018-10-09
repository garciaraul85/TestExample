package com.android.testcode.features;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.testcode.R;
import com.android.testcode.features.ui.listusers.ListUsersViewModel;
import com.android.testcode.features.ui.listusers.UserAdapter;
import com.android.testcode.features.ui.register.RegisterViewModel;
import com.android.testcode.injection.component.DaggerAppComponent;
import com.android.testcode.injection.module.AppModule;
import com.android.testcode.injection.module.RoomModule;
import com.android.testcode.util.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListUsersActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Inject
    ViewModelFactory videoViewModelFactory;
    private ListUsersViewModel listUsersViewModel;

    private RecyclerView userRecyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_users_activity);

        inject();
    }

    private void inject() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);

        addRecycler();

        listUsersViewModel = ViewModelProviders.of(this, videoViewModelFactory).get(ListUsersViewModel.class);

        listUsersViewModel.getUsersResponseLiveData().observe(this, response -> {
            adapter.addAll(response);
        });
        listUsersViewModel.loadUsers();
    }

    private void addRecycler() {
        userRecyclerView = findViewById(R.id.userList);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.setAdapter(adapter = new UserAdapter(new ArrayList<>(), R.layout.item));
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        userRecyclerView.setLayoutManager(linearLayoutManager);
        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
