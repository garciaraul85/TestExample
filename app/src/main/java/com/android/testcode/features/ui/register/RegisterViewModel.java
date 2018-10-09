package com.android.testcode.features.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.testcode.data.DataManager;
import com.android.testcode.data.dao.UserDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends ViewModel {
    private UserDataSource userDataSource;

    private final MutableLiveData<String> responseLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    public RegisterViewModel(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void loadGreeting() {
        disposables.add(
                userDataSource.getGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        responseLiveData::setValue
                )
        );
    }

    public MutableLiveData<String> getResponseLiveData() {
        return responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}