package com.android.testcode.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.android.testcode.data.dao.UserDAO;
import com.android.testcode.data.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();

}