package com.android.testcode.features.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.android.testcode.data.DataManager;
import com.android.testcode.data.dao.UserDataSource;
import com.android.testcode.data.model.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterVM";
    private UserDataSource userDataSource;

    private final MutableLiveData<String> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> userNameResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> passwordResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> userNameErrorResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> passwordErrorResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<User>> usersResponseLiveData = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

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

    public void registerUser(String userName, String password) {
        if (!validateEmail(userName)) {
            userNameResponseLiveData.setValue("Not a valid email address!");
            userNameErrorResponseLiveData.setValue(true);
        } else if (!validatePassword(password)) {
            passwordResponseLiveData.setValue("Not a valid password!");
            passwordErrorResponseLiveData.setValue(true);
        } else {
            userNameErrorResponseLiveData.setValue(false);
            passwordErrorResponseLiveData.setValue(false);
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            doRegisterUser(user);
        }
    }


    private void doRegisterUser(final User user) {
        Completable.fromAction(() -> userDataSource.insert(user))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                loadUsers();
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    private boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public MutableLiveData<String> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getUserNameResponseLiveData() {
        return userNameResponseLiveData;
    }

    public MutableLiveData<String> getPasswordResponseLiveData() {
        return passwordResponseLiveData;
    }

    public MutableLiveData<Boolean> getUserNameErrorResponseLiveData() {
        return userNameErrorResponseLiveData;
    }

    public MutableLiveData<Boolean> getPasswordErrorResponseLiveData() {
        return passwordErrorResponseLiveData;
    }

    public MutableLiveData<List<User>> getUsersResponseLiveData() {
        return usersResponseLiveData;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}