package com.android.testcode.features.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.testcode.data.dao.UserDataSource;
import com.android.testcode.data.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterVM";
    private UserDataSource userDataSource;

    private final MutableLiveData<Boolean> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> userNameResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> passwordResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> userNameErrorResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> passwordErrorResponseLiveData = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z]).{5,12}$";
    private static final String CHARACTER_PATTERN = "(\\w{2,})\\1";
    private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher emailPatcher;
    private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private Matcher passwordPatcher;
    private Pattern characterPattern = Pattern.compile(CHARACTER_PATTERN);
    private Matcher characterPatcher;

    public RegisterViewModel(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
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
                responseLiveData.postValue(true);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    private boolean validateEmail(String email) {
        emailPatcher = emailPattern.matcher(email);
        return emailPatcher.matches();
    }

    private boolean validatePassword(String password) {
        passwordPatcher = passwordPattern.matcher(password);
        characterPatcher = characterPattern.matcher(password);
        return passwordPatcher.matches() && (!characterPatcher.matches());
    }

    public MutableLiveData<Boolean> getResponseLiveData() {
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

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}