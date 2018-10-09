package com.android.testcode.data.dao;

import com.android.testcode.data.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class UserDataSource implements UserRepository {

    private static final String TAG = "UserDataSource";
    private UserDAO userDao;

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

}