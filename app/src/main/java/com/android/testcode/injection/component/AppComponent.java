package com.android.testcode.injection.component;

import android.app.Application;
import android.content.Context;

import com.android.testcode.data.AppDatabase;
import com.android.testcode.data.DataManager;
import com.android.testcode.data.dao.UserRepository;
import com.android.testcode.features.ListUsersActivity;
import com.android.testcode.features.RegisterActivity;
import com.android.testcode.injection.ApplicationContext;
import com.android.testcode.injection.module.AppModule;
import com.android.testcode.injection.module.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, RoomModule.class })
public interface AppComponent {

    void inject(RegisterActivity registerActivity);
    void inject(ListUsersActivity listUsersActivity);

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();

    AppDatabase appDataBase();

    UserRepository userRepository();
}