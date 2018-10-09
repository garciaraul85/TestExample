package com.android.testcode.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.android.testcode.data.dao.UserDataSource;
import com.android.testcode.features.ui.register.RegisterViewModel;
import com.android.testcode.data.DataManager;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private UserDataSource userDataSource;

    @Inject
    public ViewModelFactory(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RegisterViewModel.class)    return (T) new RegisterViewModel(userDataSource);
        throw new RuntimeException("Cannot create an instance of " + modelClass, new ClassNotFoundException("Class not supported in ViewModelFactory"));
    }
}
