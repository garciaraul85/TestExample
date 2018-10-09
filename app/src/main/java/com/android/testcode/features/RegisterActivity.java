package com.android.testcode.features;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.testcode.MVVMApplication;
import com.android.testcode.R;
import com.android.testcode.features.ui.register.RegisterViewModel;
import com.android.testcode.injection.component.DaggerAppComponent;
import com.android.testcode.injection.module.ActivityModule;
import com.android.testcode.injection.module.AppModule;
import com.android.testcode.injection.module.RoomModule;
import com.android.testcode.util.ViewModelFactory;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Inject
    ViewModelFactory videoViewModelFactory;
    private RegisterViewModel registerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        inject();

        registerViewModel.getResponseLiveData().observe(this, response -> {
            Log.d(TAG, "onCreate: " + response);
        });

        registerViewModel.loadGreeting();
    }

    private void inject() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);

        registerViewModel = ViewModelProviders.of(this, videoViewModelFactory).get(RegisterViewModel.class);

        Log.d(TAG, "hello");
    }

}