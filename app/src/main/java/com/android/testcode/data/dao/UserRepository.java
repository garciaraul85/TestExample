package com.android.testcode.data.dao;

import android.arch.lifecycle.LiveData;

import com.android.testcode.data.model.User;

import java.util.List;

public interface UserRepository {
    LiveData<List<User>> findAll();

    long insert(User user);
}