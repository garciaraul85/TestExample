package com.android.testcode.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.android.testcode.features.ui.register.RegisterViewModel;
import com.android.testcode.injection.data.DataManager;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private DataManager dataManager;

    @Inject
    public ViewModelFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RegisterViewModel.class)    return (T) new RegisterViewModel(dataManager);
        throw new RuntimeException("Cannot create an instance of " + modelClass, new ClassNotFoundException("Class not supported in ViewModelFactory"));
    }
}
