package com.android.testcode.data.dao;

import com.android.testcode.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

public interface UserRepository {
    Flowable<List<User>> findAll();

    void insert(User user);
}