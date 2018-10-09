package com.android.testcode.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.testcode.data.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}