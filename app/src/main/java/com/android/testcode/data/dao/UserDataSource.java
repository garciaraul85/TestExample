package com.android.testcode.data.dao;

import android.arch.lifecycle.LiveData;

import com.android.testcode.data.model.User;

import java.util.List;

import javax.inject.Inject;

public class UserDataSource implements UserRepository {

    private UserDAO userDao;

    @Inject
    public UserDataSource(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public LiveData<List<User>> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public long insert(User user) {
        return userDao.insertUser(user);
    }

}