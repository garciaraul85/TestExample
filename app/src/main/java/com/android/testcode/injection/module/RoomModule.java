package com.android.testcode.injection.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.android.testcode.data.AppDatabase;
import com.android.testcode.data.dao.UserDAO;
import com.android.testcode.data.dao.UserDataSource;
import com.android.testcode.data.dao.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase demoDatabase;

    public RoomModule(Application mApplication) {
        demoDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "users-db").build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return demoDatabase;
    }

    @Singleton
    @Provides
    UserDAO providesUserDao(AppDatabase demoDatabase) {
        return demoDatabase.userDao();
    }

    @Singleton
    @Provides
    UserRepository productRepository(UserDAO userDAO) {
        return new UserDataSource(userDAO);
    }

}