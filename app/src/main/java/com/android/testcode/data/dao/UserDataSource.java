package com.android.testcode.data.dao;

import android.util.Log;

import com.android.testcode.data.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class UserDataSource implements UserRepository {

    private static final String TAG = "UserDataSource";
    private UserDAO userDao;

    public UserDataSource () {
        Log.d(TAG, "UserDataSource: ");
    }

    @Inject
    public UserDataSource(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public Flowable<List<User>> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public void insert(User user) {
        userDao.insertUser(user);
    }

    public Single<String> getGreeting() {
        return Single.just("Hello from CommonGreetingRepository");
    }

}