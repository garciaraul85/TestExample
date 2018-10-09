package com.android.testcode.features.ui.listusers;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.testcode.data.dao.UserDataSource;
import com.android.testcode.data.model.User;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListUsersViewModel extends ViewModel {
    private UserDataSource userDataSource;
    private final MutableLiveData<List<User>> usersResponseLiveData = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    public ListUsersViewModel(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void loadUsers() {
        disposables.add(
                userDataSource.findAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                usersResponseLiveData::setValue
                        )
        );
    }


    public MutableLiveData<List<User>> getUsersResponseLiveData() {
        return usersResponseLiveData;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}