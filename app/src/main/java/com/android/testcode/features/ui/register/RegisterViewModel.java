package com.android.testcode.features.ui.register;

import android.arch.lifecycle.ViewModel;

import com.android.testcode.injection.data.DataManager;

public class RegisterViewModel extends ViewModel {
    private DataManager dataManager;


    public RegisterViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}