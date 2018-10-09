package com.android.testcode.features.ui.register;

import android.arch.lifecycle.ViewModel;

import com.android.testcode.data.DataManager;
import com.android.testcode.data.dao.UserDataSource;

public class RegisterViewModel extends ViewModel {
    private UserDataSource userDataSource;


    public RegisterViewModel(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }
}