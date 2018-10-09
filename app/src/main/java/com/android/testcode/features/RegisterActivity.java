package com.android.testcode.features;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.android.testcode.R;
import com.android.testcode.features.ui.register.RegisterViewModel;
import com.android.testcode.injection.component.DaggerAppComponent;
import com.android.testcode.injection.module.AppModule;
import com.android.testcode.injection.module.RoomModule;
import com.android.testcode.util.ViewModelFactory;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Inject
    ViewModelFactory videoViewModelFactory;
    private RegisterViewModel registerViewModel;

    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;
    private Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        usernameWrapper = findViewById(R.id.userNameWrapper);
        passwordWrapper = findViewById(R.id.passwordWrapper);
        registerBtn = findViewById(R.id.register);

        inject();

        registerViewModel.getResponseLiveData().observe(this, response -> {
            Log.d(TAG, "onCreate: " + response);
        });

        registerViewModel.getUserNameErrorResponseLiveData().observe(this, response -> {
            usernameWrapper.setErrorEnabled(response);
        });

        registerViewModel.getUserNameResponseLiveData().observe(this, response -> {
            usernameWrapper.setError(response);
        });

        registerViewModel.getPasswordErrorResponseLiveData().observe(this, response -> {
            passwordWrapper.setErrorEnabled(response);
        });

        registerViewModel.getPasswordResponseLiveData().observe(this, response -> {
            passwordWrapper.setError(response);
        });

        registerBtn.setOnClickListener(v -> {
            hideKeyboard();

            String username = usernameWrapper.getEditText().getText().toString();
            String password = passwordWrapper.getEditText().getText().toString();
            registerViewModel.registerUser(username, password);
        });

        registerViewModel.getResponseLiveData().observe(this, response -> {
            if (response) {
                Intent myIntent = new Intent(this, ListUsersActivity.class);
                startActivity(myIntent);
            }
        });
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

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}